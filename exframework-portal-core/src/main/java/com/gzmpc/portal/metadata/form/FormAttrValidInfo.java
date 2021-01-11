package com.gzmpc.portal.metadata.form;

/**
 *
 * <p>Title: Form对象里面的控件校验信息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author rwe
 * @version 1.0
 */
public class FormAttrValidInfo {
  private String name;//控件名
  private String required;//是否必填项
  private String title;//中文名
  private String validator;//校验类型//暂支持 整型、浮点型、时间型、email
  private String maxlength;//允许可输入的最大长度
  private String tooltip;//鼠标停止不动时提示信息。考虑一下提示信息=tooltip+maxlength不空
  private String precision;//精度
  private String shortcutkey;//快捷键
  public FormAttrValidInfo() {
  }

  public String getMaxlength() {
    return maxlength;
  }
  public String getName() {
    return name;
  }
  public String getRequired() {
    return required;
  }
  public String getTitle() {
    return title;
  }
  public String getTooltip() {
    return tooltip;
  }
  public String getValidator() {
    return validator;
  }
  public void setValidator(String validator) {
    this.validator = validator;
  }
  public void setTooltip(String tooltip) {
    this.tooltip = tooltip;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setRequired(String required) {
    this.required = required;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setMaxlength(String maxlength) {
    this.maxlength = maxlength;
  }

  public static void main(String[] args) {
    FormAttrValidInfo formAttrValidInfo1 = new FormAttrValidInfo();
  }
  public String getPrecision() {
    return precision;
  }
  public void setPrecision(String precision) {
    this.precision = precision;
  }
  public String getShortcutkey() {
    return shortcutkey;
  }
  public void setShortcutkey(String shortcutkey) {
    this.shortcutkey = shortcutkey;
  }


}