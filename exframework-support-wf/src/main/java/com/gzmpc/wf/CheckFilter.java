package com.gzmpc.wf;

import java.util.Map;

import bsh.Interpreter;

/**
 * 对审批角色进行筛选，得出实际的审批人
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public interface CheckFilter {
  /**
   * role可以是账号或角色，如果是角色已role:开头
   *
   * @param interpreter
   * @param doc
   * @param dtl
   * @param util
   * @return 返回值是审批信息,如判断为不需审批则返回null
   *
   */
  public StringBuffer filterCheck(Interpreter interpreter,Map doc, Map []dtl,Map util);
}
