package com.gzmpc.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.service.SequenceService;
import com.gzmpc.utils.Const;

@Configuration
@PropertySource(value=Const.SYSTEM_PROPERTIES)
@Service
public class SequenceServiceImpl implements SequenceService {
	
	private Log log = LogFactory.getLog(SequenceServiceImpl.class.getName());

	@Value("${dbseq.increment:30}")
	private int increment;
	
	public final String SEQ_INT_PRIFIX = "seq_int:";
	public final String SEQ_STR_PRIFIX = "seq_str:";

	@Autowired
	SystemDao systemDao;
	
	@Override
	public Object getNextValue(String initValue) {
		if (initValue.startsWith(SEQ_INT_PRIFIX)) {
			int next = -1;
			String seqid = initValue.substring(SEQ_INT_PRIFIX.length());
			String sql = "select noname, nolength, notype, nextid from sys_no where noid = ? for update";
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			try{
				con = systemDao.getConnection();
				con.setAutoCommit(false);
				pst = con.prepareStatement(sql);
				pst.setString(1, seqid);
				rs = pst.executeQuery();
				if(rs.next()) {
					next = rs.getInt("nextid");
				}
				rs.close();
				pst.close();
				if(next != -1) {
					pst = con.prepareStatement("update sys_no set nextid = nextid + 1 where noid = ?");
					pst.setString(1, seqid);
					pst.executeUpdate();
					pst.close();
				}
				con.commit();
			} catch ( SQLException sqle) {
				log.error("生成序列号失败:"+sqle.getMessage(),sqle);
				try{
					if( con != null && !con.isClosed()) {
						con.rollback();
					}
				} catch ( SQLException sqle2) {
					log.error("回滚失败:"+sqle.getMessage(),sqle);
				}
			} finally {
				DbUtils.closeQuietly(con, pst, rs);
			}
			return next;
		} else if (initValue.startsWith(SEQ_STR_PRIFIX)) {

		}
		return null;
	}

}
