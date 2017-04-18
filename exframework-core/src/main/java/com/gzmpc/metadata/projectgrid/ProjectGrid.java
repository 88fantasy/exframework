package com.gzmpc.metadata.projectgrid;

import com.gzmpc.metadata.MDObject;
/**
 * 功能相关数据表格配置
 * CLQ
 * 20100409
 * */
public class ProjectGrid extends MDObject {
	
	private String project_code; //功能编码
	private String sort;//更新顺序
	private String gridcode;//表格编码
	private String tablename;//表名
	private String pkname;//主键
	private String relname;//相关列在本表的列名
	private String mastertablename;//相关表名
	private String masterpkname;//相关表中的相关列
	private String mastergridcode;//相关表格编码
	
	private String position;//位置
	private String ismainwin;//是否主窗口
	private String formname;//表单名--对应grid
	
	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getGridcode() {
		return gridcode;
	}

	public void setGridcode(String gridcode) {
		this.gridcode = gridcode;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getPkname() {
		return pkname;
	}

	public void setPkname(String pkname) {
		this.pkname = pkname;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getMastertablename() {
		return mastertablename;
	}

	public void setMastertablename(String mastertablename) {
		this.mastertablename = mastertablename;
	}

	public String getMasterpkname() {
		return masterpkname;
	}

	public void setMasterpkname(String masterpkname) {
		this.masterpkname = masterpkname;
	}

	public String getMastergridcode() {
		return mastergridcode;
	}

	public void setMastergridcode(String mastergridcode) {
		this.mastergridcode = mastergridcode;
	}

	public ProjectGrid(){}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIsmainwin() {
		return ismainwin;
	}

	public void setIsmainwin(String ismainwin) {
		this.ismainwin = ismainwin;
	}

	public String getFormname() {
		return formname;
	}

	public void setFormname(String formname) {
		this.formname = formname;
	}

}
