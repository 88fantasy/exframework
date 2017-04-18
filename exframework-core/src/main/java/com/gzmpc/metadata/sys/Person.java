package com.gzmpc.metadata.sys;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 4933578292921471083L;
public Person() {
  }

  private Long gender;//性别
  private Long personId;//人员表ID
  private Long deptid;//部门ID
  private String deptno;//部门编码
  private String deptname;//部门名称
  private String personName;//人员姓名
  private String postcode;//邮编
  private String duty;//职务
  private String opcode;//人员编码
  private String spell;//拼音
  private String idNumber;//有效证件号码
  private Date birthDate;//生日
  private String email1;//电子邮件1
  private String email2;//电子邮件2
  private String email3;//电子邮件3
  private String mobile;//手机
  private String tel;//固话
  private String address;//联系地址
  private String memo;//备注
  private String barcode;//条形码
  private Long entryid;//经营单位
  private String qqno;//QQ号码
  private Short personstatus;//状态
  private Date joindate;//入职时间
  private Date leavedate;
  private String imuser;//jabber账号
  private String impsw;//jabber密码
  public String getAddress() {
    return address;
  }
  public String getBarcode() {
    return barcode;
  }
  public Date getBirthDate() {
    return birthDate;
  }
  public Long getDeptid() {
    return deptid;
  }
  public String getDeptname() {
    return deptname;
  }
  public String getDeptno() {
    return deptno;
  }
  public String getDuty() {
    return duty;
  }
  public String getEmail1() {
    return email1;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public Long getEntryid() {
    return entryid;
  }
  public Long getGender() {
    return gender;
  }
  public String getIdNumber() {
    return idNumber;
  }
  public Date getJoindate() {
    return joindate;
  }
  public Date getLeavedate() {
    return leavedate;
  }
  public String getMemo() {
    return memo;
  }
  public String getMobile() {
    return mobile;
  }
  public String getOpcode() {
    return opcode;
  }
  public Long getPersonId() {
    return personId;
  }
  public String getPersonName() {
    return personName;
  }
  public Short getPersonstatus() {
    return personstatus;
  }
  public String getPostcode() {
    return postcode;
  }
  public String getQqno() {
    return qqno;
  }
  public String getSpell() {
    return spell;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public void setSpell(String spell) {
    this.spell = spell;
  }
  public void setQqno(String qqno) {
    this.qqno = qqno;
  }
  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }
  public void setPersonstatus(Short personstatus) {
    this.personstatus = personstatus;
  }
  public void setPersonName(String personName) {
    this.personName = personName;
  }
  public void setPersonId(Long personId) {
    this.personId = personId;
  }
  public void setOpcode(String opcode) {
    this.opcode = opcode;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }
  public void setLeavedate(Date leavedate) {
    this.leavedate = leavedate;
  }
  public void setJoindate(Date joindate) {
    this.joindate = joindate;
  }
  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }
  public void setGender(Long gender) {
    this.gender = gender;
  }
  public void setEntryid(Long entryid) {
    this.entryid = entryid;
  }
  public void setEmail3(String email3) {
    this.email3 = email3;
  }
  public void setEmail2(String email2) {
    this.email2 = email2;
  }
  public void setEmail1(String email1) {
    this.email1 = email1;
  }
  public void setDuty(String duty) {
    this.duty = duty;
  }
  public void setDeptno(String deptno) {
    this.deptno = deptno;
  }
  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }
  public void setDeptid(Long deptid) {
    this.deptid = deptid;
  }
  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getImpsw() {
    return impsw;
  }
  public String getImuser() {
    return imuser;
  }
  public void setImpsw(String impsw) {
    this.impsw = impsw;
  }
  public void setImuser(String imuser) {
    this.imuser = imuser;
  }////////////////////////////////////////////////////////离职时间

}