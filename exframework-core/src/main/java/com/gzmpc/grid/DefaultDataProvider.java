package com.gzmpc.grid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.exception.InitException;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.grid.Grid;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.service.GridService;
import com.gzmpc.support.common.exception.BuildException;
import com.gzmpc.support.common.util.SpringContextUtils;
import com.gzmpc.utils.Const;

@Component
public abstract class DefaultDataProvider implements IDataProvider {
	
	private Log log = LogFactory.getLog(DefaultDataProvider.class.getName());
	
	@Autowired
	MetaData metaData;
	
	@Autowired
	SystemDao systemDao;
	
	@Value("${file.download.default.query:download/querytemp}")
	private String defaultPath;

	abstract public Map<String, Object> getJsonData(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException;

	abstract public List<Map<String, Object>> retGridInfo(String gridcode, Account account, Map<String, Object> params);

	public String getDbname(String gridcode,Map<String,Object> param) throws InitException{
		Grid gridDef = metaData.findGridDefByCode(gridcode);
		// 取GRID的DBNAME
		String dbbean = gridDef.getDbbeanname();
		if (dbbean!= null && !"".equals(dbbean.trim())) {
			IDataProviderDBnameSupport dbsup = null;
			try{
				dbsup = SpringContextUtils.getBeanById(dbbean, IDataProviderDBnameSupport.class);
			} catch (BeansException e) {
				throw new InitException("找不到dbbean["+dbbean+"]实现类");
			}
			return dbsup.retDbname(param);
		}
		else {
			return null;
		}
	}
	
	@Override
	public String doDownLoad(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException {
		Grid gridDef = metaData.findGridDefByCode(gridcode);
		
		List<Object[]> result = new ArrayList<Object[]>();
		List<Map<String, Object>> header = retGridInfo(gridcode, account, params);
		String[] headers = new String[header.size()];
		for (int i = 0, j = header.size(); i < j; i++) {
			headers[i] = (String) header.get(i).get(GridService.HEADER);
		}
		result.add(headers);
		
		if(!params.containsKey(Const.GRID_START_INDEX)){
			params.put(Const.GRID_START_INDEX, Const.NOPAGINATION);
		}
		if(!params.containsKey(Const.GRID_PAGESIZE)){
			params.put(Const.GRID_PAGESIZE, Integer.MAX_VALUE);
		}
		
		Map<String, Object> data = getJsonData(gridcode, params, account);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> rows = (List<Map<String, Object>>) data.get(Const.ROWS);
		for (int i = 0, j = header.size(); i < j; i++) {
			Map<String, Object> row = rows.get(i);
			Object[] lines = new Object[header.size()];
			for (int x = 0, y = header.size(); x < y; x++) {
				String name = (String) header.get(x).get(GridService.FIELDNAME);
				@SuppressWarnings("unchecked")
				Map<String,Object> col = (Map<String, Object>) row.get(name);
				lines[x] = col.get(GridService.RENDER);
			}
			result.add(lines);
		}
		
		String mname = systemDao.getTemplate().queryForObject("select max(a.module_name) from sys_module a, sys_module b where  a.module_id = b.pmodule_id  and  b.module_id = ? ", String.class, gridDef.getQuerymoduleid());
		if(mname == null) {
			mname = gridDef.getDataSource();
		}
		
		String filename = "/"+mname + "_" + (new Date().getTime()) + ".csv";
		String path = Const.getProjectPath()+defaultPath;
		new File(path).mkdirs();
		String file = path+filename;
		
		try{
			File f = new File(file);
			FileOutputStream fos = new FileOutputStream(f);
			// 写BOM 不然会乱码
			fos.write(new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF });
			// 创建字节流输出对象
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			CSVPrinter printer = new CSVPrinter(osw, CSVFormat.EXCEL);
			for (int i = 0, j = result.size(); i < j; i++) {
				Object[] lines = result.get(i);
				printer.printRecord(lines);
			}
			printer.flush();
			printer.close();
			return file;
		} catch ( IOException e) {
			log.error("生成csv文件时出现错误:"+e.getMessage(),e);
			throw new BuildException("生成csv文件时出现错误:"+ e.getMessage());
		}
	}

	/* 
	 * 判断总页数
	 */
	public int countTotalPage(int total, int pagesize) {
		int totalpage = 0;
		int mod = total % pagesize;
		if (mod == 0) { // 即总数目不超过每页显示条数
			totalpage = ((int)(total / pagesize)) ;
		} else { // 总数目超过每页显示条数
			totalpage = (int)(total / pagesize) + 1;
		}
		return totalpage;
	}
}
