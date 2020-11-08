package com.gzmpc.metadata.func;


import com.gzmpc.metadata.MDObject;
import com.gzmpc.metadata.projectgrid.ProjectGrid;

/**
 *
 * <p>
 * Title:定义每个功能内容
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author rwe
 * @version 1.0
 */
public class Func extends MDObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4737190249563624736L;

	private String projecttype;// 单实体、多实体、查询界面
	private String querycode;// 搜索功能用的关键字
	private String extendLayout;// 继承的布局
	private ProjectGrid[] projectgrids;// 相关表格配置
//	private List jsonprojectgrids;// json存放
	private String apimplclass;// 授权实现类
	private String opentityclass;// 功能
	private String moduleid;
	private String path;

	public ProjectGrid[] getProjectgrids() {
		return projectgrids;
	}

	public void setProjectgrids(ProjectGrid[] projectgrids) {
		this.projectgrids = projectgrids;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public String getQuerycode() {
		return querycode;
	}

	public void setQuerycode(String querycode) {
		this.querycode = querycode;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getExtendLayout() {
		return extendLayout;
	}

	public void setExtendLayout(String extendLayout) {
		this.extendLayout = extendLayout;
	}

//	public List getJsonprojectgrids() {
//		return jsonprojectgrids;
//	}
//
//	public void setJsonprojectgrids(List jsonprojectgrids) {
//		this.jsonprojectgrids = jsonprojectgrids;
//	}
//
//	public void retJsonprojectgrids(ProjectGrid[] projectgrid) {
//		jsonprojectgrids = new ArrayList();
//		if (projectgrids == null)
//			return;
//		int length = projectgrid.length;
//		for (int i = 0; i < length; i++) {
//			ProjectGrid pg = projectgrid[i];
//			Map map = new ConcurrentHashMap();
//			map.put("project_code", pg.getProject_code());
//			map.put("sort", pg.getSort());
//			map.put("gridcode", pg.getGridcode());
//			map.put("tablename", pg.getTablename());
//			map.put("pkname", pg.getPkname());
//			map.put("relname", pg.getRelname());
//			map.put("mastertablename", pg.getMastertablename());
//			map.put("masterpkname", pg.getMasterpkname());
//			map.put("mastergridcode", pg.getMastergridcode());
//			jsonprojectgrids.add(map);
//		}
//	}

	public String getApimplclass() {
		return apimplclass;
	}

	public void setApimplclass(String apimplclass) {
		this.apimplclass = apimplclass;
	}

	public String getOpentityclass() {
		return opentityclass;
	}

	public void setOpentityclass(String opentityclass) {
		this.opentityclass = opentityclass;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/***
	 * 查找projectgrid配置
	 * 
	 * @author CLQ
	 * @param gridcode
	 * @return 20120614
	 */
	public ProjectGrid findProjectGridByGridcode(String gridcode) {
		if (projectgrids == null)
			return null;
		int len = projectgrids.length;
		for (int i = 0; i < len; i++) {
			ProjectGrid pg = projectgrids[i];
			if (pg.getGridcode() != null && pg.getGridcode().equals(gridcode))
				return pg;
		}
		return null;

	}

	public ProjectGrid findProjectGridByFormcode(String formcode) {
		if (projectgrids == null)
			return null;
		int len = projectgrids.length;
		for (int i = 0; i < len; i++) {
			ProjectGrid pg = projectgrids[i];
			if (pg.getFormname() != null && pg.getFormname().equals(formcode)) {
				return pg;
			}
		}
		return null;
	}
}