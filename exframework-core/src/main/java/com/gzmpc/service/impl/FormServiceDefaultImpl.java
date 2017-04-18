package com.gzmpc.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.DbDao;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.di.DispTypeAdapter;
import com.gzmpc.metadata.di.dt.DispType;
import com.gzmpc.metadata.form.Form;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.service.FormService;
import com.gzmpc.service.SequenceService;
import com.gzmpc.util.SpringContextUtils;

import bsh.Interpreter;

@Service
public class FormServiceDefaultImpl implements FormService {

	private Log log = LogFactory.getLog(FormServiceDefaultImpl.class.getName());
	
	@Autowired
	MetaData metaData;
	
	@Autowired
	SequenceService sequenceService;
	
	@Autowired
	DispTypeAdapter dispTypeAdapter;

	/**
	 * 具有特殊意义的字串值前缀
	 */
	public final String SEQ_PRIFIX = "seq_";
	public final String ORA_SEQ_PRIFIX = "ora:";
	public final String ORA_SQL_PRIFIX = "sql:";
	public final String BSH_STR_PRIFIX = "bsh:";

	@Override
	public Map<String, Object> getFormDefaultValue(Account account, String formcode) {
		Map<String, Object> result = new ConcurrentHashMap<String, Object>();
		Form f = metaData.findFormByCode(formcode);
		if (f == null) {
			throw new IllegalArgumentException("没有找到form配置,formcode:" + formcode);
		}
		Attribute[] attrs = f.getAttributes();
		int length = attrs.length;
		for (int i = 0; i < length; i++) {
			Attribute attr = attrs[i];
			String initValue = attr.getInitValue();
			if (initValue != null) {
				Object v;
				try {
					v = calcInitValue(account, initValue);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					throw new IllegalArgumentException("初始化字段"+attr.getCode()+"时出现错误["+e.getMessage()+"]");
				}
				result.put(attr.getCode(), v);
			} else if (initValue == null) {
				result.put(attr.getCode(), "");
			}
		}
		return result;
	}

	private Object calcInitValue(Account account, String initValue) throws Exception {
		Object o = null;
		if (initValue == null) {
			return null;
		}
		// 数字序列号
		if (initValue.startsWith(SEQ_PRIFIX) ) {
			o = sequenceService.getNextValue(initValue);
		} else if (initValue.startsWith(ORA_SEQ_PRIFIX)) { 
			String seqid = initValue.substring(ORA_SEQ_PRIFIX.length());
			String dbname = "systemDao";
			int idx = seqid.indexOf("@");
			if (idx != -1) {
				dbname = seqid.substring(idx+1);
				seqid = seqid.substring(0, idx);
			}
			DbDao dao = SpringContextUtils.getBeanById(dbname, DbDao.class);
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			try{
				con = dao.getConnection();
				con.setAutoCommit(false);
				pst = con.prepareStatement("select " + seqid + ".nextval from dual");
				rs = pst.executeQuery();
				rs.next();
				o = rs.getInt(1);
				rs.close();
				pst.close();
				con.commit();
			}catch(SQLException sqle) {
				log.error("取oracle序列号失败:"+sqle.getMessage(),sqle);
				try {
					if(con != null && !con.isClosed()) {
						con.rollback();
					}
				}catch (SQLException sqle2) {
					log.error(sqle2.getMessage(),sqle2);
				}
			}finally {
				DbUtils.closeQuietly(con, pst, rs);
			}
				
//		} else if (initValue.startsWith(ORA_SQL_PRIFIX)) { // 字符序列号
//			String seqid = initValue.substring(ORA_SQL_PRIFIX.length());
//			if (!seqid.contains("#"))
//				v = PubService.query("select (" + seqid + ") seq from dual")[0].get("seq").toString();
//			else {
//				String[] sps = seqid.split("#");
//				v = PubService.query("select (" + sps[0] + ") seq from dual", sps[1])[0].get("seq").toString();
//			}
		} else if (initValue.startsWith(BSH_STR_PRIFIX)) { // 字符序列号
			String script = initValue.substring(BSH_STR_PRIFIX.length());
			//formInitUtil
			Object util = SpringContextUtils.getBeanById("formInitUtil");
			Interpreter bsh = new Interpreter();
			bsh.set("account", account);
			bsh.set("util", util);
			o = bsh.eval(script);
		} else { // 其他
			o = initValue;
		}
		return o;
	}
	
	public Map<String,Object> showAttr(Form form,Attribute formAttr) {
		DispType display = dispTypeAdapter.retDispType(formAttr.getDi().getDisptypecfg());
		return display.retDisplay(form, formAttr);
	}
}
