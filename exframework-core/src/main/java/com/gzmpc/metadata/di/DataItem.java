package com.gzmpc.metadata.di;

import com.gzmpc.metadata.MDObject;

/**
 * 数据项
 */
public class DataItem extends MDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4460273388483569516L;
	
	private String disptypecfg;// 显示风格
	private String disptypekey;// 风格的关键值（如果是querylist形的，就配置key,如果是sqllist形的就配置sqllist,如果是list形的，就直接配置list列表内容)
	private String validataTypecfg;// 校证输入值类型。可以为int,email,float形等
	private String maxlength;//////////// 允许可输入的最长字节。默认取CDM里生成的信息
	private String precision;// 精度
	private String sequence;// 查询顺序
	private String querysql;// 查询语句

	public String getDisptypecfg() {
		return disptypecfg;
	}

	public String getDisptypekey() {
		return disptypekey;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public void setDisptypekey(String disptypekey) {
		this.disptypekey = disptypekey;
	}

	public void setDisptypecfg(String disptypecfg) {
		this.disptypecfg = disptypecfg;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getValidataTypecfg() {
		return validataTypecfg;
	}

	public void setValidataTypecfg(String validataTypecfg) {
		this.validataTypecfg = validataTypecfg;
	}

	public String getQuerySql() {
		return querysql;
	}

	public void setQuerySql(String querysql) {
		this.querysql = querysql;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}
