package com.gzmpc.portal.pub.utils;

public class StringUtil {
  public StringUtil() {
  }
  //get the last name of two from package
  public static String getSystemNameFromPackageName(String packageName){
    char[] chars = packageName.toCharArray();
    String tempStr = "";
    for (int i = 0; i < chars.length; i++){
      if (chars[i] == 46){
        chars[i] = 44;
      }
      tempStr += chars[i];
    }
    String[] strings = tempStr.split(",");
    String reStr = strings[strings.length - 2];
    return reStr;

  }


  //first char to upper
  public static String firstCharToUpper(String str){
    String reStr = str.substring(0,1);
    reStr = reStr.toUpperCase();
    reStr += str.substring(1);
    return reStr;
  }
  //add 'get', and first char to upper
  public static String getGetString(String str){
    String reStr = str.substring(0,1);
    reStr = reStr.toUpperCase();
    reStr += str.substring(1);
    reStr = "get" + reStr;
    return reStr;
  }
  //add 'set' and first char to upper
  public static String getSetString(String str){
    String reStr = str.substring(0,1);
    reStr = reStr.toUpperCase();
    reStr += str.substring(1);
    reStr = "set" + reStr;
    return reStr;
  }
  //cut longclassname to classname
  public static String getNameFromClassName(String str){
    char[] chars = str.toCharArray();
    String tempStr = "";
    for (int i = 0; i < chars.length; i++){
      if (chars[i] == 46){
        chars[i] = 44;
      }
      tempStr += chars[i];
    }
    String[] strings = tempStr.split(",");
    String reStr = strings[strings.length - 1];
    return reStr;
  }
  //cut longclassname to classnamepath
  public static String getPathFromClassName(String longClassName){
    char[] chars = longClassName.toCharArray();
    String tempStr = "";
    for (int i = 0; i < chars.length; i++){
      if (chars[i] == 46){
        chars[i] = 44;
      }
      tempStr += chars[i];
    }
    String[] strings = tempStr.split(",");
    String reStr = "";
    for (int i = 0; i < strings.length - 1; i++){
      if (reStr.equals("")){
        reStr += strings[i];
      } else {
        reStr += "." + strings[i];
      }
    }
    return reStr;
  }
  //cut longclassname to classnamepath  ,cut class name and parent directory
  public static String getPathFromClassNameCutPDir(String longClassName){
    char[] chars = longClassName.toCharArray();
    String tempStr = "";
    for (int i = 0; i < chars.length; i++){
      if (chars[i] == 46){
        chars[i] = 44;
      }
      tempStr += chars[i];
    }
    String[] strings = tempStr.split(",");
    String reStr = "";
    for (int i = 0; i < strings.length - 2; i++){
      if (reStr.equals("")){
        reStr += strings[i];
      } else {
        reStr += "." + strings[i];reStr.toLowerCase();
      }
    }
    return reStr;
  }



  public String trimName(String fullName) {
    int pos = fullName.indexOf(".");

    while (pos != -1) {
      fullName = fullName.substring(pos + 1);
      pos = fullName.indexOf(".");
    }
    return fullName;
  }

  public String upperFirstChar(String className, String name, String type) {
    String tempStr = name.substring(0, 1);
    tempStr = tempStr.toUpperCase() + name.substring(1);
    String retStr = className + ".get" + tempStr + "()";
    if ("String".equalsIgnoreCase(type)) {
      return retStr;
    }
    else {
      return "String.valueOf(" + retStr + ")";
    }
  }

  public String lowerFirstChar(String name) {
    String firstChar = name.substring(0, 1).toLowerCase();
    StringBuffer retName = new StringBuffer();
    retName.append(firstChar);
    retName.append(name.substring(1, name.length()));

    return retName.toString();
  }

  public String getSetterName(String name) {
    String tempStr = name.substring(0, 1);
    tempStr = tempStr.toUpperCase() + name.substring(1);
    String retStr = ".set" + tempStr;
    return retStr;
  }

  public String getDataType(String type) {
    char fType = type.charAt(0);
    String retType = null;

    switch (fType) {
      case 'N':
        retType = "Long";
        break;
      case 'V':
        retType = "String";
        break;
      case 'D':
        retType = "Date";
        break;
      default:
        retType = type;
    }
    return retType;
  }

  public String getDaoType(String type) {
    char fType = type.charAt(0);
    String retType = null;

    switch (fType) {
      case 'N':
        retType = "integer";
        break;
      case 'V':
        retType = "char";
        break;
      case 'D':
        retType = "date";
        break;
      default:
        retType = type;
    }
    return retType;

  }

  public String getPkey(String identifier) {
    int start_pos = identifier.indexOf("(");
    int end_pos = identifier.indexOf(")");
    String retKey = identifier.substring(start_pos + 1, end_pos);
    return retKey;
  }

  /** rwe add
   * 将所有字母转化为小写
   * @param name
   * @return
   */
  public String toLowerString(String name) {
    return name.toLowerCase();
  }



}
