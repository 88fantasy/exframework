package com.gzmpc.metadata;

import java.sql.Connection;
import java.util.Map;

import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.exception.ExceptionDef;
import com.gzmpc.metadata.form.Form;
import com.gzmpc.metadata.func.Func;
import com.gzmpc.metadata.grid.Grid;
import com.gzmpc.metadata.nav.Nav;
import com.gzmpc.metadata.query.QueryDef;
import com.gzmpc.metadata.queryparam.QueryParam;
import com.gzmpc.metadata.toolbar.ToolBar;

/**
 * metadata包中存放的是元数据的定义信息，元数据定义信息的存储方式是不限的， 可以是在配置文件中，也可以是在数据库表中，但是元数据的结构是规定好的，
 * 都包含DataItem,entity,query,form,relationship等几部分。
 * 所以，元数据的解析与使用是分离开的，解析的工作由HandlerFactory的实现类来完成，
 * newInstance实现中对文件进行解析，将所有的信息读入内存中，并组织到一个
 * MetaDataHandler对象中，元数据中的所有描述类都将自己的属性暴漏出来所以可 以容易的将解析出的信息保存到相应的类实例中。
 * save的实现是一个相反的过程，他将MetaDataHandler实例中的数据保存到存储介质中。
 *
 * retFactory方法返回一个工厂对象 步骤
 * <li>查找系统属性中"HandlerFactoryImpl"定义，作为此factory实现类</li>
 * <li>若上一步失败，采用默认的
 * "com.est.helixcore.metadata.defaultImpl.HandlerFactoryDefaultImpl" 作为实现类</li>
 */
public interface HandlerFactory {

	/**
	 * 读取工程配置
	 * 
	 * @param con
	 * @return
	 */
	public Func[] retFuncDefs(Connection con);

	/**
	 * 读取查询语句配置
	 * 
	 * @param con
	 *            数据库连接
	 * @return 返回定义好的查询语句
	 */
	public QueryDef[] retQueryDefs(Connection con);

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
	public Form[] retForms(Connection con);

	/**
	 * 读取菜单栏配置 算法根据上与读取FORM配置算法一样,请参考
	 */
	public ToolBar[] retToolBars(Connection con);

	/**
	 * 读取所有queryparam配置 按每一个queryparam单独列出
	 */
	public QueryParam[] retQueryParams(Connection con);

	public Grid[] retGridDefs(Connection con);

	public DataItem[] retDataItems(Connection con);

	public Map<String, Map<String, DataItem>> retDataItemEntends(Connection con);
	
	public Nav[] retNavs(Connection con, Func[] funcs);
	
	public Map<String, ExceptionDef> retExceptions(Connection con);
	
}