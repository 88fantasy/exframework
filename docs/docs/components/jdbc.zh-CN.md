
# Jdbc

## 快速上手

提供对MybatisPlus功能的增强,并依靠 mbp 对关系型数据库的支持, 需配合数据库驱动进行使用,目前支持一下数据库:

1. mysql (exframework-support-jdbc-mysql)
2. oracle (exframework-support-jdbc-oracle)

## 安装

```xml
<dependency>
    <groupId>org.exframework</groupId>
    <artifactId>exframework-support-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.exframework</groupId>
    <artifactId>exframework-support-jdbc-mysql</artifactId>
</dependency>
<dependency>
    <groupId>org.exframework</groupId>
    <artifactId>exframework-support-jdbc-oracle</artifactId>
</dependency>
```

## 注解

### @AutoCurrentTime

通过配合`CurrentTimeMetaObjectHandler`对字段自动进行时间设置, 一般可用于通用底层类

使用实例

```java
public class BaseEntity implements Serializable {

    @AutoCurrentTime
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @AutoCurrentTime
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
```

### @EntityFieldChecker

通过配合`EntityFieldCheckerMetaObjectHandler`在数据进行入库时进行最后的校验, 比如关联主键是否存在
可与请求校验(`RequestFieldChecker`)采用同一个校验类

使用示例

```java
@TableName("uniresearch_project_export_log")
public class UniresearchProjectExportLog extends BaseEntity {

    /**
     * 项目主键
     */
    @EntityFieldChecker(ProjectIdChecker.class)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String projectId;

}
```

## 拦截器

提供 MyBatisPlus 以外的增强拦截器作为补充

|                拦截器                 |             说明             |
| :-----------------------------------: | :--------------------------: |
|    `CurrentTimeMetaObjectHandler`     |       自动设置当前时间       |
| `EntityFieldCheckerMetaObjectHandler` | 数据进行入库时进行最后的校验 |
|  `TableLogicInsertMetaObjectHandler`  |   逻辑删除增强, 自动插入值   |
|  `TableLogicInsertMetaObjectHandler`  |   逻辑版本增强, 自动插入值   |

使用示例, 通过设置统一的拦截器列表进行启用,**注意!** 项目中`PrimaryMetaObjectHandler`只能设置一个,否则会报错

```java
@Component
public class PrimaryMetaObjectHandler implements MetaObjectHandler {

    private MetaObjectHandler[] handlers = new MetaObjectHandler[]{new CurrentTimeMetaObjectHandler(),
            new EntityFieldCheckerMetaObjectHandler(), new TableLogicInsertMetaObjectHandler(), new TableVersionInsertMetaObjectHandler()};

    @Override
    public void insertFill(MetaObject metaObject) {
        Arrays.stream(handlers).forEach(o -> o.insertFill(metaObject));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Arrays.stream(handlers).forEach(o -> o.updateFill(metaObject));
    }

}
```

