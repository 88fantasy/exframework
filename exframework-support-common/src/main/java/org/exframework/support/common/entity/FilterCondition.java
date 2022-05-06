package org.exframework.support.common.entity;

import org.exframework.support.common.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Stream;

public class FilterCondition {

    /**
     * 字段
     */
    private String key;

    /**
     * 操作符
     */
    private FilterConditionOper oper;

    /**
     * 条件值
     */
    private Object filterValue;

    /**
     * 数据类型
     */
    private FilterConditionDataType filterDataType;

    FilterCondition() {

    }

    public FilterCondition(String key, Object filterValue) {
        this(key, defaultOper(filterValue), filterValue);
    }

    public FilterCondition(String key, FilterConditionOper oper, Object filterValue) {
        this(key, oper, filterValue, defaultType(filterValue));
    }

    public FilterCondition(String key, FilterConditionOper oper, Object filterValue,
                           FilterConditionDataType filterDataType) {
        super();
        this.key = key;
        this.oper = oper;
        this.filterValue = filterValue;
        this.filterDataType = filterDataType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FilterConditionOper getOper() {
        return oper;
    }

    public void setOper(FilterConditionOper oper) {
        this.oper = oper;
    }

    public Object getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(Object filterValue) {
        this.filterValue = filterValue;
    }

    public FilterConditionDataType getFilterDataType() {
        return filterDataType;
    }

    public void setFilterDataType(FilterConditionDataType filterDataType) {
        this.filterDataType = filterDataType;
    }

    public static FilterConditionOper defaultOper(Object value) {
        if (value != null) {
            Class<?> c = value.getClass();
            if (c.isArray() || Collection.class.isAssignableFrom(c)) {
                return FilterConditionOper.IN;
            }
            switch (c.getName()) {
                case "java.lang.String":
                    return FilterConditionOper.MATCHING;
                default:
                    return FilterConditionOper.EQUAL;
            }
        } else {
            return FilterConditionOper.EQUAL;
        }
    }

    public static FilterConditionDataType defaultType(Object value) {
        if (value != null) {
            Class<?> c = value.getClass();
            if (c.isArray() || Collection.class.isAssignableFrom(c)) {
                return FilterConditionDataType.LIST;
            }
            switch (c.getName()) {
                case "java.lang.Integer":
                case "java.lang.Long":
                case "java.lang.Double":
                    return FilterConditionDataType.NUMBER;
                case "java.lang.Boolean":
                    return FilterConditionDataType.BOOLEAN;
                case "java.util.Date":
                    return FilterConditionDataType.DATETIME;
                default:
                    return FilterConditionDataType.STRING;
            }
        } else {
            return FilterConditionDataType.STRING;
        }
    }

    public static Collection<FilterCondition> fromDTO(Object editable) {
        if(RequestIgnoreList.class.isAssignableFrom(editable.getClass())) {
            RequestIgnoreList ignoreObj = (RequestIgnoreList) editable;
            return fromDTO(editable, ignoreObj.ignoreList());
        } else {
            return fromDTO(editable, Collections.emptyList());
        }
    }

    public static FilterCondition[] arrayFromDTO(Object editable) {
        if(RequestIgnoreList.class.isAssignableFrom(editable.getClass())) {
            RequestIgnoreList ignoreObj = (RequestIgnoreList) editable;
            return arrayFromDTO(editable, ignoreObj.ignoreList());
        } else {
            return arrayFromDTO(editable, Collections.emptyList());
        }
    }

    public static FilterCondition[] arrayFromDTO(Object editable, Collection<String> ignoreList) {
        Collection<FilterCondition> conditions = fromDTO(editable, ignoreList);
        return conditions == null ? new FilterCondition[0] : conditions.toArray(new FilterCondition[conditions.size()]);
    }

    public static Collection<FilterCondition> fromDTO(Object editable, Collection<String> ignoreList) {
        List<FilterCondition> fcList = new ArrayList<FilterCondition>();
        final Class editableClass = editable.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(editableClass);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(editable.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null) {
                        ResolvableType sourceResolvableType = ResolvableType.forMethodReturnType(readMethod);
                        ResolvableType targetResolvableType = ResolvableType.forMethodParameter(writeMethod, 0);
                        if (targetResolvableType.isAssignableFrom(sourceResolvableType)) {
                            try {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }
                                Object value = readMethod.invoke(editable);
                                if (value == null || value.getClass().isAssignableFrom(Page.class)) {
                                    continue;
                                }
                                FilterCondition fc = new FilterCondition(targetPd.getName(), value);
                                Field field = ReflectionUtils.findField(editableClass, targetPd.getName());
                                if (field != null) {
                                    if (field.isAnnotationPresent(ConditionBetween.class)) {
                                        if (value.getClass().isArray()) {
                                            Optional<Object> optional = Stream.of((Object[]) value).findFirst();
                                            if (optional.isPresent()) {
                                                fc = new FilterCondition(targetPd.getName(), FilterConditionOper.BETWEEN, value, defaultType(optional.get()));
                                            }
                                        } else if (value instanceof Collection) {
                                            Optional<Object> optional = ((Collection<Object>) value).stream().findFirst();
                                            if (optional.isPresent()) {
                                                fc = new FilterCondition(targetPd.getName(), FilterConditionOper.BETWEEN, value, defaultType(optional.get()));
                                            }
                                        } else {
                                            fc = new FilterCondition(targetPd.getName(), FilterConditionOper.BETWEEN, value);
                                        }
                                    } else if (field.isAnnotationPresent(ConditionGreater.class)) {
                                        fc = new FilterCondition(targetPd.getName(), FilterConditionOper.GREATER, value);
                                    } else if (field.isAnnotationPresent(ConditionGreaterEqual.class)) {
                                        fc = new FilterCondition(targetPd.getName(), FilterConditionOper.GREATER_EQUAL, value);
                                    } else if (field.isAnnotationPresent(ConditionLess.class)) {
                                        fc = new FilterCondition(targetPd.getName(), FilterConditionOper.LESS, value);
                                    } else if (field.isAnnotationPresent(ConditionLessEqual.class)) {
                                        fc = new FilterCondition(targetPd.getName(), FilterConditionOper.LESS_EQUAL, value);
                                    }
                                }
                                fcList.add(fc);
                            } catch (Throwable ex) {
                                throw new FatalBeanException(
                                        "Could not copy property '" + targetPd.getName() + "' from source to target",
                                        ex);
                            }
                        }
                    }
                }
            }
        }
        return fcList;
    }

    public enum FilterConditionDataType {

        STRING("string", "字符串"), LIST("list", "数组"), NUMBER("number", "数字"), BOOLEAN("boolean", "布尔"),
        JSON("json", "JSON"), DATE("date", "日期"), DATETIME("datetime", "日期时间");

        private String key;

        private String name;

        FilterConditionDataType(String key, String name) {
            this.key = key;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

    }

    public enum FilterConditionOper {

        EQUAL("equal", "等于"), GREATER("greater", "大于"), LESS("less", "小于"), BETWEEN("between", "介于"),
        GREATER_EQUAL("greater_equal", "大于等于"), LESS_EQUAL("less_equal", "小于等于"), IN("in", "包含"), NOT_IN("not_in", "不包含"),
        MATCHING("matching", "匹配"), NOT_EQUAL("not_equal", "不等于"), ISNULL("is_null", "为空"),
        IS_NOT_NULL("is_not_null", "不为空"), STR("str", "自定义");

        private String key;

        private String name;

        FilterConditionOper(String key, String name) {
            this.key = key;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

    }

}