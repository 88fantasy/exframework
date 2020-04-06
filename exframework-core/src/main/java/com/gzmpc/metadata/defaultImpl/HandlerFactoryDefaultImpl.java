package com.gzmpc.metadata.defaultImpl;

import com.gzmpc.metadata.HandlerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gzmpc.metadata.query.QueryDef;
import com.gzmpc.metadata.queryparam.QueryParam;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.form.Form;
import com.gzmpc.metadata.func.Func;
import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.exception.ExceptionDef;
import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.toolbar.ToolBar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.dbutils.DbUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.gzmpc.metadata.toolbar.ToolButton;
import com.gzmpc.metadata.projectgrid.ProjectGrid;
import com.gzmpc.utils.Const;
import com.gzmpc.metadata.grid.Grid;
import com.gzmpc.metadata.nav.Nav;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class HandlerFactoryDefaultImpl implements HandlerFactory {

	private Log log = LogFactory.getLog(HandlerFactoryDefaultImpl.class.getName());
	
	public Map<String,Func> mdEntityMap = new ConcurrentHashMap<String,Func>(); // 多实体
	public Map<String,Func> seEntityMap = new ConcurrentHashMap<String,Func>(); // 单实体
	public Map<String,Func> queryEntityMap = new ConcurrentHashMap<String,Func>(); // 查询实体
	
	/**
	 * 读取工程配置
	 * 
	 * @param con
	 * @return
	 */
	public Func[] retFuncDefs(Connection con) {
		List<Func> list = new ArrayList<Func>(); // 保存Project
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_select = new StringBuffer();
			hhsl_select.append(" select t.funccode,t.funcname,t.module_id, t.functype,");
			hhsl_select.append(" t.querycode,t.extendlayout,t.path from sys_funccfg t");
			pstm = con.prepareStatement(hhsl_select.toString());
			rs = pstm.executeQuery();

			while (rs.next()) {
				String code = rs.getString("funccode");
				String name = rs.getString("funcname");
				String type = rs.getString("functype");
				String querycode = rs.getString("querycode");
				String extendlayout = rs.getString("extendlayout");
				String path = rs.getString("path");

				Func j = new Func();
				j.setCode(code);
				j.setName(name);
				j.setProjecttype(type);
				j.setQuerycode(querycode);
				j.setExtendLayout(extendlayout);
				j.setPath(path);

//				ProjectGrid[] pgs = retProjectGrids(code, con);
//				j.setProjectgrids(pgs);
//				j.retJsonprojectgrids(pgs);
				list.add(j);

				if (type.equals(Const.PROJECT_TYPE_SE))
					seEntityMap.put(code, j);
				else if (type.equals(Const.PROJECT_TYPE_ME))
					mdEntityMap.put(code, j);
				else if (type.equals(Const.PROJECT_TYPE_QE))
					queryEntityMap.put(code, j);
			}
		} catch (Exception ex) {
			log.error("初始化project失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return list.toArray(new Func[list.size()]);

	}

	/**
	 * 读取projectgrid配置
	 * 
	 * @param projectcode
	 *            功能编码 con 数据库连接
	 * @return 返回一个projectGrid[]数组
	 */
	public ProjectGrid[] retProjectGrids(String funccode, Connection con) throws Exception {

		List<ProjectGrid> list = new ArrayList<ProjectGrid>(); // 保存ProjectGrids
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_select = new StringBuffer();
			hhsl_select.append(" select projectgrid_id, project_code, sort, gridcode, tablename,pkname, relname, mastertablename,masterpkname,mastergridcode from sys_projectgridcfg t where t.project_code = '"
							+ funccode + "' order by sort");

			pstm = con.prepareStatement(hhsl_select.toString());
			rs = pstm.executeQuery();

			while (rs.next()) {

				String project_code = rs.getString("project_code");
				String sort = rs.getString("sort");
				String gridcode = rs.getString("gridcode");
				String tablename = rs.getString("tablename");
				String pkname = rs.getString("pkname");
				String relname = rs.getString("relname");
				String mastertablename = rs.getString("mastertablename");
				String masterpkname = rs.getString("masterpkname");
				String mastergridcode = rs.getString("mastergridcode");

				ProjectGrid pg = new ProjectGrid();
				pg.setProject_code(project_code);
				pg.setSort(sort);
				pg.setGridcode(gridcode);
				pg.setTablename(tablename);
				pg.setPkname(pkname);
				pg.setRelname(relname);
				pg.setMastertablename(mastertablename);
				pg.setMasterpkname(masterpkname);
				pg.setMastergridcode(mastergridcode);

				list.add(pg);

			}
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return list.toArray(new ProjectGrid[list.size()]);

	}

	/**
	 * 读取查询语句配置
	 * 
	 * @param con
	 *            数据库连接
	 * @return 返回定义好的查询语句
	 */
	public QueryDef[] retQueryDefs(Connection con) {
		List<QueryDef> list = new ArrayList<QueryDef>(); // 保存Query
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_select = new StringBuffer();
			hhsl_select.append(" select * from sys_querycfg ");
			pstm = con.prepareStatement(hhsl_select.toString());
			rs = pstm.executeQuery();

			while (rs.next()) {
				String querycfgno = rs.getString("querycfgno"); // 必填项

				String sqldef = rs.getString("sqldef"); // 必填项

				if (querycfgno == null || "".equals(querycfgno))
					throw new RuntimeException("查询语句对应的编号不能为空");
				if (sqldef == null || "".equals(sqldef))
					throw new RuntimeException("查询语句对应的定义sql语句不能为空");

				QueryDef g = new QueryDef();
				g.setCode(querycfgno);
				g.setSqldef(sqldef);
//				g.setQuerymoduleid(querymoduleid);
				list.add(g);
			}
		} catch (SQLException sqle) {
			log.error("初始化querydef失败:"+sqle.getMessage(),sqle);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return list.toArray(new QueryDef[list.size()]);

	}

	/**
	 * 读取配置好的Form配置 算法： 1.遍历sys_formcfg与sys_attributecfg,绑定两个表,按formcode排序
	 * 2.如果发现当前的attribute与原先的attribute同属于一个FORM的,则判断FORM对象是否存在, 不存在则创建FORM.
	 * 随后则把attribute放到一个列表里面,作为FORM的attribute属性
	 * 3.如果发现当前的attribute与原先的不属于同一个FORM的,则需要把原先的FORM放到list对象缓存起来.
	 * 同时也要把之前的attribute列表赋予给Form.然后再清空attribute列表,从新初始化一个新FORM
	 * 4.遍历完数据后,最后一条记录肯定是没有保存的,所有最终仍需要判断一下FORM是否空,把它保存起来
	 *
	 * @param con
	 *            数据库连接
	 * @return 返回定义好的FORM定义
	 */
	public Form[] retForms(Connection con) {

		Map<String, Map<String, DataItem>> dataItemExtends = retDataItemEntends(con);
		DataItem[] dataItems = retDataItems(con);
		List<Form> list = new ArrayList<Form>(); // 保存Form
		PreparedStatement pst = null,pst2 = null;
		ResultSet rs = null,rs2 = null;
		try {
			StringBuffer hhsl_selectAttribute = new StringBuffer();
			hhsl_selectAttribute.append(" select f.formcode,f.formname,f.isvaliddata,");
			hhsl_selectAttribute.append(" attributeid,attributecode,required,initvalue,");
			hhsl_selectAttribute.append("readonly,visualaidconfig,tooltip,dtcode,shortcutkey,onclickquery from ");
			hhsl_selectAttribute.append(" sys_attributecfg a, sys_formcfg f where a.formcode = f.formcode ");
			hhsl_selectAttribute.append(" order by a.formcode,a.sort");
			pst = con.prepareStatement(" select f.formcode,f.formname,f.isvaliddata from sys_formcfg f");

			rs = pst.executeQuery();
			while ( rs.next()) {
				String formcode = rs.getString(1);
				Form form = new Form();
				form.setCode(formcode);
				form.setName(rs.getString(2));
				form.setIsvaliddata(rs.getInt(3)==1?true:false);
				
				List<Attribute> attrList = new ArrayList<Attribute>(); // 保存每个FORM对应的所有ATTR属性
				
				pst2 = con.prepareStatement("select attributeid,attributecode,required,initvalue,readonly,visualaidconfig,tooltip,dtcode,shortcutkey,onclickquery from sys_attributecfg whre formcode = ? order by sort");
				pst2.setString(1, formcode);
				rs2 = pst2.executeQuery();
				while(rs2.next()) {
					String attributeid = rs.getString("attributeid");
					String attributecode = rs.getString("attributecode");
					boolean required = rs.getInt("required")==1;
					String initvalue = rs.getString("initvalue");
					boolean readonly = rs.getInt("readonly")==1;
					String visualaidconfig = rs.getString("visualaidconfig");
					String tooltip = rs.getString("tooltip");
					String dtcode = rs.getString("dtcode");
					String shortcutkey = rs.getString("shortcutkey");
					String onclickquery = rs.getString("onclickquery");

					Attribute attr = new Attribute();
					attr.setAttributeid(new Long(attributeid));
					attr.setCode(attributecode);
					attr.setDataItemCode(dtcode);
					attr.setInitValue(initvalue);
					attr.setTooltip(tooltip);
					attr.setReadonly(readonly);
					attr.setRequired(required);
					attr.setVisualaidconfig(visualaidconfig);
					attr.setShortcutkey(shortcutkey);
					attr.setOnclickquery(onclickquery);
					attr.setFormcode(formcode);
					DataItem di = null;
					Map<String, DataItem> formMap = dataItemExtends.get(formcode);
					if(formMap != null) {
						di = formMap.get(dtcode);
					}
					if( di == null) {
						for( DataItem d : dataItems) {
							if(dtcode.equals(d.getCode())) {
								di = d;
								break;
							}
						}
					}
					attr.setDi(di);
					attrList.add(attr);
				}
				rs2.close();
				pst2.close();
				
				Attribute[] formattr = attrList.toArray(new Attribute[attrList.size()]);
				form.setAttributes(formattr);
				list.add(form);
			}
			rs.close();
			pst.close();
				
		} catch (SQLException ex) {
			log.error("初始化form失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pst2, rs2);
			DbUtils.closeQuietly(null, pst, rs);
		}
		return list.toArray(new Form[list.size()]);

	}

	/**
	 * 读取菜单栏配置 算法根据上与读取FORM配置算法一样,请参考
	 * 
	 * @param con
	 *            数据库连接
	 * @return 返回菜单栏配置
	 */
	public ToolBar[] retToolBars(Connection con)  {
		List<ToolBar> list = new ArrayList<ToolBar>(); // 保存Form
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_selectToolbutton = new StringBuffer();
			hhsl_selectToolbutton.append(" select f.toolbarcode,f.toolbarname,");
			hhsl_selectToolbutton.append(" toolbuttonid,toolbartype,title,texthandler,");
			hhsl_selectToolbutton.append(
					"tooltip,iconcls,shortcutkey,module_id from ");
			hhsl_selectToolbutton.append(" sys_toolbarcfg f, sys_toolbuttoncfg a where a.toolbarcode = f.toolbarcode ");
			hhsl_selectToolbutton.append(" order by a.toolbarcode,a.buttonsort,a.toolbuttonid");
			pstm = con.prepareStatement(hhsl_selectToolbutton.toString());

			String toolbarcode = "";
			rs = pstm.executeQuery();

			List<ToolButton> toolbuttonList = new ArrayList<ToolButton>(); // 保存按钮操作属性

			ToolBar toolbar = null;
			while (rs.next()) {
				String toolbarcode1 = rs.getString("toolbarcode");
				String toolbarname = rs.getString("toolbarname");
				String toolbartype = rs.getString("toolbartype");
				long buttonid = rs.getLong("toolbuttonid");
				String title = rs.getString("title");
				String texthandler = rs.getString("texthandler");
				String tooltip = rs.getString("tooltip");
				String iconcls = rs.getString("iconcls");
				String shortcutkey = rs.getString("shortcutkey");
				String moduleId = rs.getString("module_id");

				ToolButton tb = new ToolButton();
				tb.setButtonid(buttonid);
				tb.setIconcls(iconcls);
				tb.setTitle(title);
				tb.setTexthandler(texthandler);
				tb.setToolbartype(toolbartype);
				tb.setTooltip(tooltip);
				tb.setShortcutkey(shortcutkey);
				tb.setModuleId(moduleId);

				if ("".equals(toolbarcode) || toolbarcode1.equals(toolbarcode)) { // 如果toolbarcode为空或者上一条记录与当前是同属于同一个ToolBar的
					if (toolbar == null) {
						toolbar = new ToolBar();
						toolbar.setCode(toolbarcode1);
						toolbar.setName(toolbarname);
					}
					toolbuttonList.add(tb);
				} else { // 如果是不同的ToolBar,先保存上一个ToolBar的配置，然后再配置新的ToolBar
					ToolButton[] toolbtn = toolbuttonList.toArray(new ToolButton[toolbuttonList.size()]);
					toolbar.setToolButtons(toolbtn);
					list.add(toolbar);
					toolbuttonList.clear();
					// 配置新的ToolBar
					toolbar = new ToolBar();
					toolbar.setCode(toolbarcode1);
					toolbar.setName(toolbarname);
					toolbuttonList.add(tb);
				}
				toolbarcode = toolbarcode1;

			}
			if (toolbar != null) { // 最后一条记录，不会自动清空ToolBar的，但需要把配置也读进来
				ToolButton[] toolbtn = (ToolButton[]) toolbuttonList.toArray(new ToolButton[toolbuttonList.size()]);
				toolbar.setToolButtons(toolbtn);
				list.add(toolbar);

			}
		} catch (SQLException ex) {
			log.error("初始化toolbar失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return list.toArray(new ToolBar[list.size()]);

	}

	public QueryParam[] retQueryParams(Connection con)  {
		List<QueryParam> list = new ArrayList<QueryParam>(); // 保存QueryParam
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_selectQueryParam = new StringBuffer();
			hhsl_selectQueryParam.append(" select a.queryparamcode,a.funccode,a.queryparamname,");
			hhsl_selectQueryParam.append(
					" b.paramitemid,b.queryparamcode,b.qpitype,b.queryname,b.queryoper,b.queryfield,b.querysort,b.initvalue,b.tooltip,b.tablename,b.ischildrenfield,b.defaultconditionjs,b.snsretcolname,b.dbbean ");
			hhsl_selectQueryParam.append(" from sys_queryparamcfg a,sys_queryparamitemcfg b ");
			hhsl_selectQueryParam.append(" where a.queryparamcode = b.queryparamcode ");
			hhsl_selectQueryParam.append(" order by a.queryparamcode,b.querysort");
			pstm = con.prepareStatement(hhsl_selectQueryParam.toString());
			String queryparamcode = "";
			rs = pstm.executeQuery();

			List<QueryParamItem> queryparamitemList = new ArrayList<QueryParamItem>(); // 查询配置字段列表

			QueryParam queryparam = null;
			while (rs.next()) {
				String queryparamcode1 = rs.getString("queryparamcode");
				String queryparamname = rs.getString("queryparamname");
				String qpitype = rs.getString("qpitype");
				String queryname = rs.getString("queryname");
				String queryoper = rs.getString("queryoper");
				String queryfield = rs.getString("queryfield");
				String initvalue = rs.getString("initvalue");
				String tooltip = rs.getString("tooltip");
				String tablename = rs.getString("tablename");
				String ischildrenfield = rs.getString("ischildrenfield");
				String defaultconditionjs = rs.getString("defaultconditionjs");
				String snsretcolname = rs.getString("snsretcolname");
				String funccode = rs.getString("funccode");
				String dbbean = rs.getString("dbbean");

				QueryParamItem qpi = new QueryParamItem();
				qpi.setQueryfield(queryfield);
				qpi.setQueryname(queryname);
				qpi.setQueryoper(queryoper);
				qpi.setQueryparamcode(queryparamcode1);
				qpi.setQpitype(qpitype);
				qpi.setInitvalue(initvalue);
				qpi.setTooltip(tooltip);
				qpi.setTablename(tablename);
				qpi.setIschildrenfield(ischildrenfield);
				qpi.setDefaultconditionjs(defaultconditionjs);
				qpi.setSnsretcolname(snsretcolname);
				qpi.setDbbean(dbbean);

				if ("".equals(queryparamcode) || queryparamcode1.equals(queryparamcode)) { // 如果queryparamcode为空或者上一条记录与当前是同属于同一个queryparam的
					if (queryparam == null) {
						queryparam = new QueryParam();
						queryparam.setCode(queryparamcode1);
						queryparam.setName(queryparamname);
						queryparam.setFunccode(funccode);
					}
					queryparamitemList.add(qpi);
				} else { // 如果是不同的QueryParam,先保存上一个QueryParam的配置，然后再配置新的QueryParam

					QueryParamItem[] qpis = (QueryParamItem[]) queryparamitemList
							.toArray(new QueryParamItem[queryparamitemList.size()]);
					queryparam.setQueryParamItems(qpis);
					list.add(queryparam);
					queryparamitemList.clear();
					// 配置新的QueryParam
					queryparam = new QueryParam();
					queryparam.setCode(queryparamcode1);
					queryparam.setName(queryparamname);
					queryparam.setFunccode(funccode);
					queryparamitemList.add(qpi);
				}
				queryparamcode = queryparamcode1;

			}
			if (queryparam != null) { // 最后一条记录，不会自动清空QueryParam的，但需要把配置也读进来
				QueryParamItem[] qpis = queryparamitemList.toArray(new QueryParamItem[queryparamitemList.size()]);
				queryparam.setQueryParamItems(qpis);
				list.add(queryparam);

			}
		} catch (SQLException ex) {
			log.error("初始化queryparam失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return list.toArray(new QueryParam[list.size()]);

	}

	@SuppressWarnings("resource")
	public Grid[] retGridDefs(Connection con)  {
		List<Grid> list = new ArrayList<Grid>(); // 保存Project
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_select = new StringBuffer();
			hhsl_select.append(" select * from sys_grid ");
			pstm = con.prepareStatement(hhsl_select.toString());
			rs = pstm.executeQuery();

			while (rs.next()) {
				String toolbarcode = rs.getString("toolbarcode");
				String funccode = rs.getString("funccode");
				String gridcode = rs.getString("gridcode");
				String datasource = rs.getString("datasource"); // 必填项
				String dataIndex = rs.getString("dataIndex");
				String autoquery = rs.getString("autoquery");
				String pagesize = rs.getString("pagesize"); 
				String querytype = rs.getString("querytype"); // 必填项
				String gridname = rs.getString("gridname");
				String querymoduleid = rs.getString("querymoduleid"); 
				String downloadmoduleid = rs.getString("downloadmoduleid");
				String sumfieldnames = rs.getString("sumfieldnames");
				int selectmodel = rs.getInt("selectmodel");
				String defaultToolbar = rs.getString("defaulttoolbar");
				String extendbeanname = rs.getString("extendbeanname");
				String orderby = rs.getString("orderby");
				String dbbeanname = rs.getString("dbnamebeanname");
				int needpagecount = rs.getInt("needpagecount");
				String afterquerybeanname = rs.getString("afterquerybeanname");

				if (selectmodel == 0 ) {
					selectmodel = Const.GRID_SELECT_MODEL_MULTI;
				}
				if (datasource == null || "".equals(datasource)) {
					throw new SQLException("数据源不能为空");
				}
				if (pagesize == null ) {
					pagesize = "50,100,500,infinity";
				}
				if (querytype == null || "".equals(querytype)) {
					throw new SQLException("查询类别不能为空，现允许支持table,query");
				}
				if (querymoduleid == null || "".equals(querymoduleid)) {
					querymoduleid = "all";
				}
				if (downloadmoduleid == null || "".equals(downloadmoduleid)) {
					downloadmoduleid = "all";
				}

				Grid g = new Grid();
				g.setToolbarCode(toolbarcode);
				g.setFuncCode(funccode);
				g.setGridCode(gridcode);
				g.setDataSource(datasource);
				g.setDataIndex(dataIndex);
				g.setAutoQuery(autoquery);
				g.setPagesize(pagesize);
				g.setQueryType(querytype);
				g.setGridname(gridname);
				g.setQuerymoduleid(querymoduleid);
				g.setDownloadmoduleid(downloadmoduleid);
				g.setSumfieldnames(sumfieldnames);
				g.setSelectmodel(selectmodel);
				g.setDefalutToolbar(defaultToolbar);
				g.setExtendBeanName(extendbeanname);
				g.setOrderby(orderby);
				g.setDbbeanname(dbbeanname);
				g.setNeedpagecount(needpagecount==1);
				g.setAfterQueryBeanName(afterquerybeanname);
				list.add(g);
			}
			rs.close();
			pstm.close();
			
		} catch (SQLException ex) {
			log.error("初始化grid失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return list.toArray(new Grid[list.size()]);
	}

	public void checkAttrDataItem(Connection con) throws SQLException {
		String hhsl_checkDataItem = "select dtcode from sys_attributecfg g where dtcode not in(select dtcode from sys_dataitemcfg union select dtcode from sys_dataitemcfg_extend) ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(hhsl_checkDataItem);
			rs = pstm.executeQuery();
			StringBuffer buf = new StringBuffer();
			while (rs.next()) {
				buf.append(rs.getString("dtcode"));
				buf.append(",");
			}
			if (buf.length() > 0)
				throw new RuntimeException(
						"以下DATAITEMCODE：（" + buf.substring(0, buf.length() - 1) + "）没有在sys_dataitemcfg配置");
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
	}

	public DataItem[] retDataItems(Connection con) {
		List<DataItem> result = new ArrayList<DataItem>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			StringBuffer hhsl_allselect = new StringBuffer();
			hhsl_allselect.append(" select * from sys_dataitemcfg ");
			pstm = con.prepareStatement(hhsl_allselect.toString());
			
			// 读取所有DataItem信息
			rs = pstm.executeQuery();

			while (rs.next()) {
				String code = rs.getString("dtcode");
				String name = rs.getString("dtname");
				String memo = rs.getString("dtmemo");
				String disptypecfg = rs.getString("disptypecfg");
				String validataTypecfg = rs.getString("validataTypecfg");
				String maxlength = rs.getString("maxlength");
				String querysql = rs.getString("querysql");
				String disptypekey = rs.getString("disptypekey");
				String sequence = rs.getString("sequence");
				String precison = rs.getString("numberprecision");

				DataItem dataitem = new DataItem();
				dataitem.setCode(code);
				dataitem.setName(name);
				dataitem.setComment(memo);
				dataitem.setDisptypecfg(disptypecfg);
				dataitem.setValidataTypecfg(validataTypecfg);
				dataitem.setMaxlength(maxlength);
				dataitem.setPrecision(precison);

				dataitem.setSequence(sequence);
				dataitem.setDisptypekey(disptypekey);
				dataitem.setQuerySql(querysql);
				result.add(dataitem);
			}
		} catch (SQLException ex) {
			log.error("初始化dataitem失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return result.toArray(new DataItem[result.size()]);
	}

	public Map<String, Map<String, DataItem>> retDataItemEntends(Connection con) {
		Map<String, Map<String, DataItem>> result = new ConcurrentHashMap<String, Map<String, DataItem>>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from sys_dataitemcfg_extend");
			rs = pst.executeQuery();
			while (rs.next()) {
				String objectcode = rs.getString("objectcode");
				String code = rs.getString("dtcode");
				String name = rs.getString("dtname");
				String memo = rs.getString("dtmemo");
				String disptypecfg = rs.getString("disptypecfg");
				String validataTypecfg = rs.getString("validataTypecfg");
				String maxlength = rs.getString("maxlength");
				String querysql = rs.getString("querysql");
				String disptypekey = rs.getString("disptypekey");
				String sequence = rs.getString("sequence");
				String precison = rs.getString("numberprecision");

				DataItem dataitem = new DataItem();
				dataitem.setCode(code);
				dataitem.setName(name);
				dataitem.setComment(memo);
				dataitem.setDisptypecfg(disptypecfg);
				dataitem.setValidataTypecfg(validataTypecfg);
				dataitem.setMaxlength(maxlength);
				dataitem.setPrecision(precison);

				dataitem.setSequence(sequence);
				dataitem.setDisptypekey(disptypekey);
				dataitem.setQuerySql(querysql);

				Map<String,DataItem> o = result.get(objectcode);
				if (o == null) {
					o = new ConcurrentHashMap<String, DataItem>();
				}
				o.put(code, dataitem);
				result.put(objectcode, o);
			}

			
		} catch (SQLException ex) {
			log.error("初始化dataitem-extend失败:"+ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return result;
	}


	public Nav[] retNavs(Connection con, Func[] funcs) {
		List<Nav> result = new ArrayList<Nav>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Nav nav = null;
		try {
			pst = con.prepareStatement("select a.navcode,a.navname,a.icon,b.funccode from sys_nav a,sys_navitem b where a.navcode = b.navcode order by a.navcode,a.ob,b.ob");
			rs = pst.executeQuery();
			while(rs.next()) {
				String navcode = rs.getString(1);
				String navname = rs.getString(2);
				String icon = rs.getString(3);
				String funccode = rs.getString(4);
				
				if(nav == null || !navcode.equals(nav.getCode())){
					if(nav != null) {
						result.add(nav);
					}
					nav = new Nav();
					nav.setCode(navcode);
					nav.setName(navname);
					nav.setIcon(icon);
					List<Func> l = new ArrayList<Func>();
					for(int i=0,j=funcs.length;i<j;i++) {
						if(funcs[i].getCode().equals(funccode)){
							l.add(funcs[i]);
							break;
						}
					}
					nav.setList(l.toArray(new Func[l.size()]));
				}
				else {
					List<Func> l = new ArrayList<Func>(Arrays.asList(nav.getList()));
					for(int i=0,j=funcs.length;i<j;i++) {
						if(funcs[i].getCode().equals(funccode)){
							l.add(funcs[i]);
							break;
						}
					}
					nav.setList(l.toArray(new Func[l.size()]));
				}
			}
			if(nav != null) {
				result.add(nav);
			}
			rs.close();
			pst.close();
			
		} catch (Exception sqle) {
			log.error(sqle.getMessage(),sqle);
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return result.toArray(new Nav[result.size()]);
	}

	@Override
	public Map<String, ExceptionDef> retExceptions(Connection con) {
		Map<String, ExceptionDef> result = new ConcurrentHashMap<String, ExceptionDef>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from sys_error_def");
			rs = pst.executeQuery();
			while (rs.next()) {
				String code = rs.getString("exceptionclass");
				String name = rs.getString("exceptionname");
				String heading = rs.getString("heading");
				String solution = rs.getString("solution");
				String path = rs.getString("topath");
				
				ExceptionDef e = new ExceptionDef();
				e.setCode(code);
				e.setName(name);
				e.setHeading(heading);
				e.setSolution(solution);
				e.setToPath(path);
				
				result.put(code, e);
			}
			rs.close();
			pst.close();
			
		} catch (SQLException sqle) {
			log.error(sqle.getMessage(),sqle);
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return result;
	}

}
