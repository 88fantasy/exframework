package com.gzmpc.wf;

import java.util.Map;

/**
 * 对审批角色进行筛选，得出实际的审批人
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public interface RoleFilter {
  /**
   * role可以是账号或角色，如果是角色已role:开头
   *
   * @param role
   * @param doc
   * @param filterDeptCode 部门信息名
   * @param dtl
   * @return返回值只可以是账号,如果找不到,则返回null
   * 没有合适的人选，返回null;
   */
  public String filterRole(String role, String filterDeptCode, Map doc, Map []dtl);
}
