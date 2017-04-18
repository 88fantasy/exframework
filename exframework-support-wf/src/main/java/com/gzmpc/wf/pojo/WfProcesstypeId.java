package com.gzmpc.wf.pojo;

public class WfProcesstypeId implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3755228720166847232L;
	
	private Long wfpid;
	private String processtype;
	private String processname;
	private String moduleid;
	private String actiondo;
	private String formcode;
	private String tablename;
	private String pkname;
	private String instance;
	private Boolean emailflag;
	private Boolean smsflag;
	private String javascript;
	private String path;
	private short dtltype;
	private String dtltable;
	private String pinstancename;
	private String docview;
	private String dbname;

	// Constructors

	/** default constructor */
	public WfProcesstypeId() {
	}

	/** minimal constructor */
	public WfProcesstypeId(Long wfpid) {
		this.wfpid = wfpid;
	}

	/** full constructor */
	public WfProcesstypeId(Long wfpid, String processtype, String processname,
			String moduleid, String actiondo, String formcode,
			String tablename, String pkname, String instance, Boolean emailflag,
			Boolean smsflag, String javascript, String path, short dtltype,
			String dtltable, String pinstancename, String docview,
			String dtlview, String dbname) {
		this.wfpid = wfpid;
		this.processtype = processtype;
		this.processname = processname;
		this.moduleid = moduleid;
		this.actiondo = actiondo;
		this.formcode = formcode;
		this.tablename = tablename;
		this.pkname = pkname;
		this.instance = instance;
		this.emailflag = emailflag;
		this.smsflag = smsflag;
		this.javascript = javascript;
		this.path = path;
		this.dtltype = dtltype;
		this.dtltable = dtltable;
		this.pinstancename = pinstancename;
		this.docview = docview;
		this.dbname = dbname;
	}

	// Property accessors

	public WfProcesstypeId(Long wfpid, String processtype,
			String processname, String moduleid, String actiondo,
			String formcode, String tablename, String pkname, String instance,
			short dtltype, String dtltable, String pinstancename,
			String docview,String dbname) {
		this.wfpid = wfpid;
		this.processtype = processtype;
		this.processname = processname;
		this.moduleid = moduleid;
		this.actiondo = actiondo;
		this.formcode = formcode;
		this.tablename = tablename;
		this.pkname = pkname;
		this.instance = instance;
		this.dtltype = dtltype;
		this.dtltable = dtltable;
		this.pinstancename = pinstancename;
		this.docview = docview;
		this.dbname = dbname;
	}

	public Long getWfpid() {
		return this.wfpid;
	}

	public void setWfpid(Long wfpid) {
		this.wfpid = wfpid;
	}

	public String getProcesstype() {
		return this.processtype;
	}

	public void setProcesstype(String processtype) {
		this.processtype = processtype;
	}

	public String getProcessname() {
		return this.processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getActiondo() {
		return this.actiondo;
	}

	public void setActiondo(String actiondo) {
		this.actiondo = actiondo;
	}

	public String getFormcode() {
		return this.formcode;
	}

	public void setFormcode(String formcode) {
		this.formcode = formcode;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getPkname() {
		return this.pkname;
	}

	public void setPkname(String pkname) {
		this.pkname = pkname;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public Boolean getEmailflag() {
		return this.emailflag;
	}

	public void setEmailflag(Boolean emailflag) {
		this.emailflag = emailflag;
	}

	public Boolean getSmsflag() {
		return this.smsflag;
	}

	public void setSmsflag(Boolean smsflag) {
		this.smsflag = smsflag;
	}

	public String getJavascript() {
		return this.javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public short getDtltype() {
		return this.dtltype;
	}

	public void setDtltype(short dtltype) {
		this.dtltype = dtltype;
	}

	public String getDtltable() {
		return this.dtltable;
	}

	public void setDtltable(String dtltable) {
		this.dtltable = dtltable;
	}

	public String getPinstancename() {
		return this.pinstancename;
	}

	public void setPinstancename(String pinstancename) {
		this.pinstancename = pinstancename;
	}

	public String getDocview() {
		return this.docview;
	}

	public void setDocview(String docview) {
		this.docview = docview;
	}

	public String getDbname() {
		return this.dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
}