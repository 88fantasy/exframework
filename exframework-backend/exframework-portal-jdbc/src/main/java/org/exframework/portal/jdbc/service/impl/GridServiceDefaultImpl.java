//package org.exframework.portal.jdbc.service.impl;
//
//import cn.hutool.core.date.DateUtil;
//import org.exframework.portal.enums.DataItemDisplayType;
//import org.exframework.portal.enums.DataItemValueType;
//import org.exframework.portal.metadata.di.DataItem;
//import org.exframework.portal.metadata.grid.Grid;
//import org.exframework.portal.metadata.sys.Account;
//import org.exframework.portal.service.sys.GridService;
//import org.exframework.portal.service.sys.PortalCoreDataItemService;
//import org.exframework.portal.service.sys.PortalCoreDdlService;
//import org.exframework.portal.service.sys.PortalCoreSystemParameterService;
//import org.exframework.support.common.entity.FilterCondition.FilterConditionOper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//public class GridServiceDefaultImpl implements GridService {
//
//    private Logger log = LoggerFactory.getLogger(GridServiceDefaultImpl.class.getName());
//
//    @Autowired
//    PortalCoreDdlService portalCoreDdlService;
//
//    @Autowired
//    PortalCoreDataItemService portalCoreDataItemService;
//
//    @Autowired
//    PortalCoreSystemParameterService portalCoreSystemParameterService;
//
//    @Override
//    public List<Map<String, Object>> drawGridTitleInfo(ResultSetMetaData rsmd, String gridcode) throws SQLException {
//        List<Map<String, Object>> result = new ArrayList<>();
//
//        Map<String, Object> rownum = new ConcurrentHashMap<>();
//        rownum.put(FIELDNAME, "rownum_");
//        rownum.put(FIELDTYPE, Types.VARCHAR);
//        rownum.put(DATATYPE, "string");
//        rownum.put(HEADER, "行号");
//        result.add(rownum);
//
//        int count = rsmd.getColumnCount();
//        for (int i = 0; i <= count - 1; i++) {
//            Map<String, Object> row = new ConcurrentHashMap<>();
//            String fieldname = rsmd.getColumnLabel(i + 1).toLowerCase();
//            row.put(FIELDNAME, fieldname);
//
//            DataItem dt = portalCoreDataItemService.findDataItem(gridcode, fieldname);
//            if (dt == null) {
//                dt = new DataItem();
//                dt.setCode(fieldname.toUpperCase());
//                dt.setName(fieldname);
//                dt.setType(DataItemDisplayType.INPUT);
//            }
//            int t = rsmd.getColumnType(i + 1);
//            int scale = rsmd.getScale(i + 1);
//            int precision = dt.getDigits();
//            DataItemValueType validtype = dt.getValueType();
//            row.put(FIELDTYPE, t);
//            row.put(HEADER, dt.getName());
//            row.put(DATATYPE, validtype.name());
//            // 要求浮点的要求能实现小数点自动对齐，因此改为字符串，但整型则不需要，可以排序
//            if (t == Types.DATE || t == Types.TIME || t == Types.TIMESTAMP) {
//                row.put(DATATYPE, DataItemValueType.DATETIME.name());
//            } else {
//                if (t == Types.INTEGER || (t == Types.NUMERIC && (scale == 0 || precision == 0))) {
//                    row.put(DATATYPE, DataItemValueType.LONG.name());
//                } else if (t == Types.FLOAT || t == Types.NUMERIC || t == Types.DOUBLE || t == Types.DECIMAL) {
//                    row.put(DATATYPE, DataItemValueType.BIGDECIMAL.name());
//                    if (precision == 0) {
//                        row.put(PRECISION, scale);
//                    } else {
//                        row.put(PRECISION, precision);
//                    }
//                } else {
//                    if (DataItemValueType.BIGDECIMAL == validtype) {
//                        if (precision == 0) {
//                            row.put(PRECISION, scale);
//                        } else {
//                            row.put(PRECISION, precision);
//                        }
//                    }
//                }
//                // 看一下是否需要控制下拉框或者复选框
//                DataItemDisplayType disptype = dt.getType();
//                switch (disptype) {
//                    case DICTIONARY:
//                        Map<String, String> dict = portalCoreDdlService.get(dt.getDisplayKey());
//                        row.put(DATATYPE, DataItemDisplayType.DICTIONARY.name());
//                        row.put(DATA, dict);
//                        break;
//                    case CHECKBOX:
//                        row.put(DATATYPE, DataItemDisplayType.CHECKBOX.name());
//                        break;
//                    case READONLY:
//                        row.put(DATATYPE, DataItemDisplayType.READONLY.name());
//                        break;
//                    case PASSWORD:
//                        row.put(DATATYPE, DataItemDisplayType.PASSWORD.name());
//                        break;
//                    case INPUT:
//                        row.put(DATATYPE, DataItemDisplayType.INPUT.name());
//                        break;
//                }
//            }
//            result.add(row);
//        }
//        return result;
//    }
//
//    @Override
//    public String makeupCondition(String oprea, String fieldname, String value1, String value2, List<Object> conditions,
//                                  List<Map<String, Object>> fields) {
//        if (oprea.equals(FilterConditionOper.STR.name())) {
//            return " (" + value1 + ") ";
//        }
//
//        Map<String, Object> field = null;
//        for (Map<String, Object> row : fields) {
//            String name = (String) row.get(FIELDNAME);
//            if (fieldname.equals(name)) {
//                field = row;
//                break;
//            }
//        }
//        if (field != null) {
//            int fieldType = (int) field.get(FIELDTYPE);
//
//            if ("rownum_".equals(fieldname)) {
//                return "";
//            }
//            boolean isDdateType = false; // 数据类型为时间类型的
//
//            if (fieldType == Types.NULL) { // 非查询条件
//                return fieldname + oprea + value1;
//            }
//            if ((fieldType == Types.DATE) || (fieldType == Types.TIME) || (fieldType == Types.TIMESTAMP)) {
//                isDdateType = true;
//            }
//
//            StringBuilder condition = new StringBuilder();
//
//            if (oprea.equals(FilterConditionOper.EQUAL.name())) { // 等于号
//                condition.append(fieldname);
//                if (!isDdateType) {
//                    condition.append(" = ? ");
//                    conditions.add(value1);
//                } else {
//                    condition.append(" between ? and ?");
//                    Date value = DateUtil.parse(value1.trim());
//                    java.sql.Date dt1 = new java.sql.Date(value.getTime());
//                    java.sql.Date dt2 = new java.sql.Date(DateUtil.offsetDay(value, 1).getTime());
//                    conditions.add(dt1);
//                    conditions.add(dt2);
//                }
//            } else if (oprea.equals(FilterConditionOper.MATCHING.name())) { // 匹配
//                condition.append(fieldname);
//                condition.append(" like ?");
//                if (!value1.endsWith(".")) { // 如果以.号为结束的表示模糊查询时后面没有%号
//                    value1 = value1 + "%";
//                } else {
//                    value1 = value1.substring(0, value1.length() - 1);
//                }
//                conditions.add(value1);
//            } else if (oprea.equals(FilterConditionOper.GREATER_EQUAL.name())) { // 大于等于
//                condition.append(fieldname);
//                if (isDdateType) {
//                    condition.append(" >= ?");
//                    Date value = DateUtil.parse(value1);
//                    java.sql.Date dt1 = new java.sql.Date(value.getTime());
//                    conditions.add(dt1);
//                } else {
//                    condition.append(" >=?");
//                    conditions.add(value1);
//                }
//            } else if (oprea.equals(FilterConditionOper.LESS_EQUAL.name())) { // 小于等于
//                condition.append(fieldname);
//                if (isDdateType) {
//                    condition.append(" < ?");
//                    Date value = DateUtil.parse(value1);
//                    java.sql.Date dt1 = new java.sql.Date(DateUtil.offsetDay(value, 1).getTime());
//                    conditions.add(dt1);
//                } else {
//                    condition.append(" <=?");
//                    conditions.add(value1);
//                }
//            } else if (oprea.equals(FilterConditionOper.IN.name())) { // in 语句
//                condition.append(fieldname);
//                if (value1.length() > 0) {
//                    String[] arr_str = value1.split(",");
//                    int length = arr_str.length;
//                    condition.append(" in (");
//                    for (int i = 0; i < length; i++) {
//                        if (i == length - 1) {
//                            condition.append(" ?");
//                        } else {
//                            condition.append(" ?,");
//                        }
//                        conditions.add(arr_str[i]);
//                    }
//                    condition.append(" )");
//                }
//            } else if (oprea.equals(FilterConditionOper.GREATER.name())) { // 大于号
//                condition.append(fieldname);
//                if (isDdateType) {
//                    condition.append(" >= ?"); // 取下一天
//                    Date value = DateUtil.parse(value1);
//                    java.sql.Date dt1 = new java.sql.Date(DateUtil.offsetDay(value, 1).getTime());
//                    conditions.add(dt1);
//
//                } else {
//                    condition.append(" > ?");
//                    conditions.add(value1);
//                }
//            } else if (oprea.equals(FilterConditionOper.LESS.name())) { // 小于号
//                condition.append(fieldname);
//                if (isDdateType) {
//                    condition.append(" < ?");
//                    Date value = DateUtil.parse(value1);
//                    java.sql.Date dt1 = new java.sql.Date(value.getTime());
//                    conditions.add(dt1);
//                } else {
//                    condition.append(" < ?");
//                    conditions.add(value1);
//                }
//            } else if (oprea.equals(FilterConditionOper.BETWEEN.name())) { // between
//                condition.append(fieldname);
//                if (isDdateType) {
//                    condition.append(" between ? and ?");
//                    Date value = DateUtil.parse(value1.trim());
//                    Date v = DateUtil.parse(value2.trim());
//                    java.sql.Timestamp dt1 = new java.sql.Timestamp(value.getTime());
//                    java.sql.Timestamp dt2 = new java.sql.Timestamp(v.getTime());
//
//                    conditions.add(dt1);
//                    conditions.add(dt2);
//                } else {
//                    condition.append(" between ? and ?");
//                    conditions.add(value1);
//                    conditions.add(value2);
//                }
//            } else if (oprea.equals(FilterConditionOper.NOT_EQUAL.name())) { // 不等于
//                condition.append(fieldname);
//                if (!isDdateType) {
//                    condition.append(" != ?");
//                    conditions.add(value1);
//                } else {
//                    condition.append(" != ?");
//                    Date value = DateUtil.parse(value1);
//                    java.sql.Date dt1 = new java.sql.Date(value.getTime());
//                    conditions.add(dt1);
//                }
//            }
//            return condition.toString();
//        } else {
//            return "";
//        }
//    }
//
//    @Override
//    public void filterGridColumns(String gridcode, Account account, List<Map<String, Object>> rows) {
////		List<String> gridroleColumns = getRoleColumnsParams(gridcode, account);
////		// 删除 过滤字段
////		for (int i = rows.size() - 1; i > -1; i--) {
////			Map<String, Object> row = rows.get(i);
////			String field = (String) row.get("fieldname");
////			if (gridroleColumns.contains(field)) {
////				rows.remove(i);
////			}
////		}
////
////		// 参数格式为(列名,)多个以逗号隔开
////		String gridConfigInfo = portalCoreSystemParameterService.getAccoutParameter(account, gridcode + COLCONFIG);
////		if (gridConfigInfo != null && !"".equals(gridConfigInfo)) {
////
////			String[] existClos = gridConfigInfo.split(",");
////			int length = existClos.length;
////			for (int i = 0; i < length; i++) {
////				String colname = existClos[i];
////
////				for (int x = 0, y = rows.size(); x < y; x++) {
////					Map<String, Object> row = rows.get(x);
////					if (colname.equals(row.get(FIELDNAME))) {
////						row.put(HIDDEN, false);
////						row.put(SORT_KEY, i);
////						break;
////					}
////				}
////			}
////			for (int i = 0, j = rows.size(); i < j; i++) {// 其它数据库里没有保存的，可能是表或视图新增的
////				Map<String, Object> row = rows.get(i);
////				if (!row.containsKey(SORT_KEY)) {
////					row.put(SORT_KEY, existClos.length + i);
////					row.put(HIDDEN, true);
////				}
////			}
////
////			final class ColumnComparator implements Comparator<Map<String, Object>> {
////				@Override
////				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
////					int sort1 = (int) o1.get(SORT_KEY);
////					int sort2 = (int) o2.get(SORT_KEY);
////					return Integer.compare(sort1, sort2);
////				}
////
////			}
////			Collections.sort(rows, new ColumnComparator());
////			for (int i = 0, j = rows.size(); i < j; i++) {// 去除临时变量
////				rows.get(i).remove(SORT_KEY);
////			}
////		}
//    }
//
////	private List<String> getRoleColumnsParams(String gridcode, Account account) {
////		List<String> result = new ArrayList<String>();
////		Connection con = null;
////		PreparedStatement pst = null;
////		ResultSet rs = null;
////		try {
////			con = systemDao.getUnautocommitConnection();
////			pst = con.prepareStatement(
////					" select distinct gridcolumncode from sys_accountrole v, sys_rolecolumns r where v.role_id = r.role_id and gridcode = ? and account_id = ? ");
////			pst.setString(1, gridcode);
////			pst.setString(2, account.getAccountId());
////			rs = pst.executeQuery();
////			while (rs.next()) {
////				result.add(rs.getString(1));
////			}
////			rs.close();
////			pst.close();
////			con.commit();
////		} catch (Exception e) {
////			log.error(e.getMessage(), e);
////			try {
////				con.rollback();
////			} catch (SQLException ex1) {
////				log.error(ex1.getMessage(), ex1);
////			}
////		} finally {
////			DbUtils.closeQuietly(con, pst, rs);
////		}
////		return result;
////	}
//
//    @Override
//    public Map<String, Object> outputOneRow(ResultSet rs, List<Map<String, Object>> headers) throws SQLException {
//        Map<String, Object> result = new ConcurrentHashMap<>();
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int count = rsmd.getColumnCount();
//        for (int i = 1; i <= count; i++) {
//            String column = rsmd.getColumnLabel(i);
//            String value = rs.getString(column);
//            if (value == null) {
//                value = "";
//            }
//            String render = value;
//            String columnname = column.toLowerCase();
//
//            Map<String, Object> header = null;
//            for (Map<String, Object> row : headers) {
//                String fieldname = (String) row.get(FIELDNAME);
//                if (columnname.equals(fieldname)) {
//                    header = row;
//                    break;
//                }
//            }
//            if (header != null) {
//                String type = (String) header.get(DATATYPE);
//                if ("float".equals(type)) {
//                    BigDecimal d = new BigDecimal(value);
//                    int precision = (int) header.get(PRECISION);
//                    d.setScale(precision, RoundingMode.HALF_UP);
//                    value = d.toString();
//                    render = value;
//                } else if ("dt".equals(type)) {
//                    if (value.endsWith("00:00:00") || value.endsWith("00:00:00.0")) {
//                        value = value.substring(0, 10);
//                    }
//                    int index = value.indexOf("."); // 删除毫秒
//                    if (index > 0) {
//                        value = value.substring(0, index);
//                    }
//                    render = value;
//                } else if ("checkbox".equals(type)) {
//                    if ("1".equals(value) || "true".equals(value)) {
//                        render = "是";
//                    } else if ("0".equals(value) || "false".equals(value)) {
//                        render = "否";
//                    } else {
//                        render = "[格式错误]";
//                    }
//                } else if ("list".equals(type)) {
//                    @SuppressWarnings("unchecked")
//                    Map<String, String> dict = (Map<String, String>) header.get(DATA);
//                    render = dict.get(value);
//                } else if ("checksqllist".equals(type)) {
//                    // todo
//                }
//            }
//            Map<String, Object> v = new ConcurrentHashMap<>();
//            v.put(VALUE, value);
//            v.put(RENDER, render);
//            result.put(columnname, v);
//        }
//        return result;
//    }
//
//    @Override
//    public Grid findByKey(String key) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//}
