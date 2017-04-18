package com.gzmpc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.*;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzmpc.mapper.LowerColumnMapRowMapper;

@Component
public class QueryUtil {
	private Log log = LogFactory.getLog(QueryUtil.class.getName());

	@Autowired
	QuerySupport querySupport;

	public Map<String, Object>[] query(String sql, String dbname, Object... args) throws SQLException {
		Connection con = null;
		try {
			con = querySupport.retConnection(dbname);
			con.setAutoCommit(false);
			Map<String, Object>[] result = query(con, sql, args);
			con.commit();
			return result;
		} catch (SQLException sqle) {
			try{
				if( con != null) {
					con.rollback();
				}
			}catch (SQLException sqle2) {
				log.error("回滚出现错误:"+sqle2.getMessage(),sqle2);
			}
			throw sqle;
		} finally {
			DbUtils.closeQuietly(con);
		}

	}
	
	public Map<String, Object> queryForObject(String sql, String dbname, Object... args) throws SQLException {
		Map<String, Object> result = null;
		Connection con = null;
		try {
			con = querySupport.retConnection(dbname);
			con.setAutoCommit(false);
			Map<String, Object>[] list = query(con, sql, args);
			if(list.length>0) {
				result = list[0];
			}
			con.commit();
			
		} catch (SQLException sqle) {
			try{
				if( con != null) {
					con.rollback();
				}
			}catch (SQLException sqle2) {
				log.error("回滚出现错误:"+sqle2.getMessage(),sqle2);
			}
			throw sqle;
		} finally {
			DbUtils.closeQuietly(con);
		}
		return result;
	}

	public Map<String, Object>[] query(Connection con, String sql, Object... args) throws SQLException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sqlobjects = "";
		try {
			log.info(sql);
			pst = con.prepareStatement(sql);
			if (args != null) {
				for (int i = 0, j = args.length; i < j; i++) {
					pst.setObject(i + 1, args[i]);
					sqlobjects += String.valueOf(args[i]) + ",";
				}
			}
			if (sqlobjects.length() > 0) {
				log.info(sqlobjects.substring(0, sqlobjects.length() - 1));
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				result.add(new LowerColumnMapRowMapper().mapRow(rs, result.size()+1));
			}
			rs.close();
			pst.close();
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}

		if (result.size() > 0) {
			@SuppressWarnings("unchecked")
			Map<String, Object>[] dataMaps = new Hashtable[result.size()];
			return result.toArray(dataMaps);
		} else {
			return null;
		}
	}

	public Map<String, String> getDict(String sql, String dbname, Object... args) throws SQLException {
		Map<String,String> dict = new ConcurrentHashMap<String,String>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = querySupport.retConnection(dbname);
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			if (args != null) {
				for (int i = 0, j = args.length; i < j; i++) {
					pst.setObject(i + 1, args[i]);
				}
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				String key = rs.getString(1);
				String value = rs.getString(2);
				dict.put(key, value);
			}
			rs.close();
			pst.close();
			con.commit();
		} catch (SQLException sqle) {
			try{
				if( con != null) {
					con.rollback();
				}
			}catch (SQLException sqle2) {
				log.error("回滚出现错误:"+sqle2.getMessage(),sqle2);
			}
			throw sqle;
		} finally {
			DbUtils.closeQuietly(con,pst,rs);
		}
		return dict;
	}

	/**
	 * 将str中所有的%param%类似的字串替换为params中的实际值,若param为date_开头，
	 * 则参数为日期型，参数值必须为yyyy-mm-dd格式, 替换的参数会转为日期型 mandatoryParam
	 * 若为true，当在params中找不到参数时，异常退出。 若为false，当在params中找不到参数时，返回null。
	 * 
	 * @param sqlstr
	 *            需替换的字符串
	 * @param params
	 *            参数
	 * @param mandatoryParam
	 *            是否必须有所有参数. 若为true，当在params中找不到参数时，异常退出。
	 *            若为false，当在params中找不到参数时，返回null。
	 * @return 替换好的字符串
	 */
	public String replaceParam(String sqlstr, Properties params, boolean mandatoryParam) throws Exception {
		String paramPattern = "\\{[a-zA-Z_0-9]*\\}";
		Pattern p = Pattern.compile(paramPattern);
		Matcher m = p.matcher(sqlstr);
		StringBuffer sb = new StringBuffer();
		boolean result = m.find();
		while (result) {
			String str = m.group();
			String aParam = str.substring(1, str.length() - 1);
			Object object = params.get(aParam);
			String value = null;
			if (object != null)
				value = params.get(aParam).toString();// rwe update
														// params.getProperty();

			if (value == null) {
				if (mandatoryParam)
					throw new Exception("缺少参数:" + aParam);
				else
					return null;
			}
			// 若param日期型，参数值必须为yyyy-mm-dd格式,替换的参数会转为日期型
			if ((aParam.length() > 5) && (aParam.substring(0, 5).equals("date_"))) {
				value = "to_date('" + value + "','yyyy-mm-dd')";
			} else

				// value="'"+value+"'";
				;
			m.appendReplacement(sb, value);
			result = m.find();
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 根据查询名称与参数，组合一个sql语句
	 * 
	 * @param params
	 *            参数
	 * @return sql语句
	 */
	public String parseSql(String sqlStr, Properties params) throws Exception {
		// 表示用[]括起来的并且其中不包含[的字符串
		final String conditionPattern = "\\[[^\\[]*\\]";

		// 进行替换，若参数没有赋值，去掉此参数关联的语句。
		Pattern p = Pattern.compile(conditionPattern);
		Matcher m = p.matcher(sqlStr);
		StringBuffer sb = new StringBuffer();
		boolean result = m.find();
		while (result) {
			String s = m.group();
			String str = replaceParam(s.substring(1, s.length() - 1), params, false);
			if (str == null) // 在[...%param%...]条件中的param,无法在params中找到
				str = " ";
			m.appendReplacement(sb, str);
			result = m.find();
		}
		m.appendTail(sb);
		// 将sql语句中的%param%换为实际值
		String resultStr = replaceParam(sb.toString(), params, true);
		return resultStr;
	}

}
