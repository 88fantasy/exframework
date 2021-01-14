package com.gzmpc.portal.utils;

import java.text.*;


/**
 * 定义常量
 * @author rwe
 *
 */
public class Const {

	public static final String SYSTEM_PROPERTIES = "classpath:system.properties";

	public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String PROJECT_TYPE_SE = "1"; // 单实体
	public static String PROJECT_TYPE_ME = "2"; // 多实体
	public static String PROJECT_TYPE_QE = "3"; // 查询实体

	public static String PROJECT_CLASSTYPE_SYS = "1"; // 平台功能系统开发；
	public static String PROJECT_CLASSTYPE_FUNC = "2"; // 系统开发；
	public static String PROJECT_CLASSTYPE_USER = "3"; // 二次开发;

	public static String PROJECT_CLASSTYPE_SYS_PATH = "sys"; // 平台功能系统开发
	public static String PROJECT_CLASSTYPE_FUNC_PATH = "func"; // 项目功能开发
	public static String PROJECT_CLASSTYPE_USER_PATH = "user"; // 二次开发

	public static String MODULE_CLASSTYPE_SYS = "1"; // 平台功能系统开发
	public static String MODULE_CLASSTYPE_FUNC = "2"; // 系统开发
	public static String MODULE_CLASSTYPE_USER = "3"; // 二次开发

	public static String DT_SHORT_CUT_KEY = "SHORTCUTKEY"; // 快捷键定义下列框值

	public static String INPUT_CSS_READONLY = "readonlyText"; // 在css/default.css中定义
	public static String INPUT_CSS_CANWRITE = "normalText"; // 在css/default.css中定义
	public static String INPUT_CSS_CALENDAR_CANWRITE = "calendarNormalText"; // 时间类型的，在css/default.css中定义
	public static String INPUT_CSS_SNS_CANWRITE = "snsNormalText"; // sns类型的，在css/default.css中定义
	// 菜单栏常用变量定义
	public static String MENU_ID = "buttonid";
	public static String MENU_TEXT = "text";
	public static String MENU_TARGET = "toggleHandler";
	public static String MENU_ICONCLS = "iconCls";
	public static String MENU_SHORTCUTKEY = "shortcutkey";
	public static String MENU_TOOLTIP = "tooltip";
	public static String MENU_DISABLED = "disabled";// clq

	// 表格画界面使用到的参数
	public static String GRID_GRID_CODE = "gridcode";
	public static String GRID_AUTO_QUERY = "autoQuery";
	public static String GRID_PAGESIZE = "pagesize";
	public static String GRID_QUERY_TYPE = "queryType";
	public static String GRID_DATA_SOURCE = "dataSource";
	public static String GRID_QUERY_MODULEID = "querymoduleid";
	public static String GRID_DOWNLOADMODULEID = "downloadmoduleid";
	public static String GRID_VISIBLE_FIELDS = "visibleFields";
	public static String GRID_SUM_FIELDNAMES = "sumfieldnames";
	public static String GRID_SELECT_MODEL = "selectmodel";
	public static String GRID_TOOLBAR_COMPOSITE = "toolbarComposite";
	public static String GRID_DEFALUT_TOOLBAR = "defaulttoolbar";
	public static String GRID_DBNAME = "griddbname";// 表格dbname 
	public static String GRID_NEEDPAGECOUNT = "needpagecount";// 是否提供总页数 
	
	
	public static int GRID_SELECT_MODEL_SINGLE = 1;	//单选
	public static int GRID_SELECT_MODEL_MULTI = 2;	//多选
	public static int GRID_SELECT_MODEL_NOSELECT = 3;	//不选
	
	public static String CONDITIONS = "conditions";
	public static String NEEDPAGECOUNT = "needpagecount";// 是否提供总页数
	public static String TOTALPAGE = "totalpage";// 总页数 isQueryLastPage
	public static String ISQUERYLASTPAGE = "isquerylastpage";
	public static String CURRENTPAGE = "currentpage";
	public static String ROWS = "rows";
	public static String TOTALROWS = "totalrows";// 总记录数
	public static String STARTINDEX = "startindex";// 页起始行
	public static String DBCLASSNAME = "dbclassname";// 用哪一个类来筛选数据库
	public static String NEEDCLOSEDBLINK = "closedblink";// 用哪一个类来筛选数据库

	public static String PARENT_GRID_CODE = "_parentgridcode";// 父表格编码

	public static String GRID_FIELDS = "fields";
	public static String GRID_START_INDEX = "startIndex";
	public static String GRID_ROLEVISIT_Columns = "gridRoleVisitColumns";
	public static String GRID_SORT_FIELDS = "sortfields";// 排序设置
	// 表格查询类别
	public static String GRID_QUERY_TYPE_TABLE = "table";
	public static String GRID_QUERY_TYPE_QUERY = "query";
	public static String GRID_QUERY_TYPE_SELFQUERY = "selfQuery"; // 暂时不使用

	public final static String PRFIX_OPERA = "oper";
	public final static String PRFIX_FIELDNAME = "fieldname";
	public final static String PRFIX_VALUE1 = "value1";
	public final static String PRFIX_VALUE2 = "value2";

	public static String PRFIX_LENGTH = "oper_length";

	public final static String GRID_OPER_FLAG = "gridOperFlag";
	public final static String GRID_OPER_FLAG_INSERT = "INSERT";
	public final static String GRID_OPER_FLAG_UPDATE = "UPDATE";
	public final static String GRID_OPER_FLAG_DELETE = "DELETE";

	
	public final static String QUERY_PARAM_ITEM_COLUNMNAME = "colname";// 查询字段中文
	public final static String QUERY_PARAM_ITEM_OPERDATA = "operdata"; // 操作相关的信息保存在此,传至ui让前端构建
	public final static String QUERY_PARAM_ITEM_COLFIELD = "colfield"; // 查询字段
	public final static String QUERY_PARAM_ITEM_OPER = "oper";
	public final static String QUERY_PARAM_ITEM_QUERYTYPE = "qpitype";
	public final static String QUERY_PARAM_ITEM_TABLENAME = "tablename";
	public final static String QUERY_PARAM_ITEM_ISCHILDRENFIELD = "ischildrenfield";
	public final static String QUERY_PARAM_ITEM_GRID_INDEXNAME = "indexname";
	public final static String QUERY_PARAM_ITEM_COMBOBOX_DATA = "comboboxdata";
	public final static String QUERY_PARAM_ITEM_DEFAULTCONDITION_JS = "defaultconditionjs";

	public final static String QPI_TYPE_STRING = "s";
	public final static String QPI_TYPE_STRINGLOWER = "sl";
	public final static String QPI_TYPE_STRINGUPPER = "su";
	public final static String QPI_TYPE_DATE = "dt";
	public final static String QPI_TYPE_DDL = "ddl";
	public final static String QPI_TYPE_V = "v";// 集合
	public final static String QPI_TYPE_T = "t";// 树形集合
	public final static String QPI_TYPE_N = "n";
	public final static String QPI_TYPE_H = "h";
	public final static String QPI_TYPE_MCOMBOX = "mcombox";
	public final static String QPI_TYPE_E = "e";

	public final static String QUERYCODE = "querycode";
	public final static String DDLKEY = "ddlkey";

	public final static String QUERY_PARAM_CODE = "queryparamcode";

	public final static int ENTITY_TYPE_TABLE = 1; // 实体类型为取自数据表
	public final static int ENTITY_TYPE_VIEW = 2; // 实体类型为取自视图

	// 工作流
	static public final short WORKFLOW_STATUS_INIT = 0; // 草稿
	static public final short WORKFLOW_STATUS_NOCHECK = 1; // 待审批
	static public final short WORKFLOW_STATUS_CHECKPASS = 2; // 审批通过
	static public final short WORKFLOW_STATUS_NOPASS = 3; // 审批未通过
	// rwe 090922
	static public final short WORKFLOW_STATUS_STOP = 4; // 审批中止

	static public final String OBE_PROCESS_SEQ = "OBE_PROCESS_SEQ"; // 流程实例ID
	static public final String OBE_ATTRIBUTE_SEQ = "OBE_ATTRIBUTE_SEQ"; // 流程属性实例ID
	static public final String OBE_WORKITEM_SEQ = "OBE_WORKITEM_SEQ"; // 工作任务ID

	static public final String SYS_LOGGER_SEQ = "SYS_LOGGER_SEQ";// 系统日志ID
	static public final String SYS_SHORTCUTMENU_SEQ = "SYS_SHORTCUTMENU_SEQ";// 快捷菜单ID
	static public final String SYS_ROLECOLUMNS_SEQ = "SYS_ROLECOLUMNS_SEQ";// 限制列对应角色查询ID
	// 工作流单据审批状态 1：初始；2：已审批；3：被中止 0:挂起
	static public final short OBE_STATE_INIT = 1;
	static public final short OBE_STATE_CHECK = 2;
	static public final short OBE_STATE_STOP = 3;
	static public final short OBE_STATE_HANG = 0;
	// 任务分配类型
	static public final short FILTERTYPE_FIXED = 1;// 固定
	static public final short FILTERTYPE_FETCH = 2;// 领用


	static public final String VALIDDATA_PRIVATEKEY = "validdata_privatekey";// 校验数据密钥
	static public final String VALIDCODENAME = "_validcode";// 校验码在记录中的字段名
	static public final String ROLE_HASHCODE = "rolehashcode";
	static public final String ROLE_ID = "role_id";
	static public final String FUNCCODE = "funccode";

	static public final String APCFGCODE = "apcfgcode";

	static public final String CURRENT_ROLE_ID = "CURRENT_ROLE_ID";

	// 常用操作属性
	static public final String OP_KEY_QUERY = "qryable";
	static public final String OP_KEY_ADD = "addable";
	static public final String OP_KEY_MODIFY = "modifyable";
	static public final String OP_KEY_DELETE = "deleteable";
	static public final String OP_KEY_SAVE = "saveable";
	static public final String OP_KEY_DOWNLOAD = "downloadable";

	static public final String AP_NAME_OP = "OP";
	static public final String AP_NAME_DISENABLECP = "DISENABLECP";
	static public final String AP_NAME_CHECKEXP = "CHECKEXP";
	static public final String AP_NAME_CALCEXP = "CALCEXP";
	static public final String AP_NAME_WHEREEXP = "WHEREEXP";
	static public final String AP_NAME_HIDDEN = "HIDDEN";

	public static int ROLEMODULETYPE_SELF = 1; // 只能查询自己的单据
	public static int ROLEMODULETYPE_SQL = 2; // 只能查询SQL定义的单据
	public static int ROLEMODULETYPE_ALL = 3; // 可以查询全部单据

	public static int LASTPAGE = -2;
	public static int NOPAGINATION = -3;
	
	public static String getProjectPath() {
		return System.getProperty("web.root");
	}
}