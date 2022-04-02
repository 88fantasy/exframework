# canal  springboot 组件

[官网文档](https://github.com/alibaba/canal)

主要应用场景
- 数据同步(数据库主从)
- 数据同步到 es 或 redis 做数据一致性
- 相当于 after insert 触发器

***

## 启用方式

- 配置文件设置 enable

```yml
canal:
    enable: true
```
   

- Application 或其他地址使用注解 `@EnableCanalClient`

```java
@EnableCanalClient
@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
```

## 配置属性
```yml

canal:
  enable: true
  client:
    instances:
      example:
        cluster-enabled: false #是否是集群模式
        zookeeper-address: 127.0.0.1:2181,127.1.1.1:2187  #zookeeper 地址
        user-name: root #集群 设置的用户名
        password: 123456 #集群 设置的密码
        host: 127.0.0.1  #canal 服务器地址，默认是本地的环回地址
        port: 11111 #canal 服务设置的端口，默认 11111
        batch-size: 1000  #单次从 canal 服务器获取数据的数量
        retry-count: 20 #当错误发生时，重试次数
        acquire-interval: 1000 #信息捕获心跳时间            
```

## 用法

- 注解方式(推荐使用)

```java

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.annotation.CanalEventListener;
import org.exframework.spring.boot.canal.annotation.content.DeleteListenPoint;
import org.exframework.spring.boot.canal.annotation.content.InsertListenPoint;
import org.exframework.spring.boot.canal.annotation.content.UpdateListenPoint;
import org.exframework.spring.boot.canal.client.core.CanalMsg;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 表单记录监听器
 *
 * @author rwe
 * @date 2022/3/15 18:03
 **/
@CanalEventListener
public class CanalListener {

    @InsertListenPoint(destination = "canal设置的instance", database = "数据库名", table = {"表名"})
    public void onEventInsertData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {
            String sql = "use " + canalMsg.getSchemaName() + ";\n";
            StringBuffer colums = new StringBuffer();
            StringBuffer values = new StringBuffer();
            rowData.getAfterColumnsList().forEach((c) -> {
                colums.append(c.getName() + ",");
                values.append("'" + c.getValue() + "',");
            });


            sql += "INSERT INTO " + canalMsg.getTableName() + "(" + colums.substring(0, colums.length() - 1) + ") VALUES(" + values.substring(0, values.length() - 1) + ");";
            System.out.println(sql);
        }

    }

    @UpdateListenPoint(destination = "example", database = "数据库名", table = {"表名"})
    public void onEventUpdateData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {

            String sql = "use " + canalMsg.getSchemaName() + ";\n";
            StringBuffer updates = new StringBuffer();
            StringBuffer conditions = new StringBuffer();
            rowData.getAfterColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    conditions.append(c.getName() + "='" + c.getValue() + "'");
                } else {
                    updates.append(c.getName() + "='" + c.getValue() + "',");
                }
            });
            sql += "UPDATE " + canalMsg.getTableName() + " SET " + updates.substring(0, updates.length() - 1) + " WHERE " + conditions;
            System.out.println(sql);
        }
    }

    @DeleteListenPoint(destination = "example", database = "数据库名", table = {"表名"})
    public void onEventDeleteData(CanalEntry.RowChange rowChange, CanalMsg canalMsg) {

        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {

            if (!CollectionUtils.isEmpty(rowData.getBeforeColumnsList())) {
                String sql = "use " + canalMsg.getSchemaName() + ";\n";

                sql += "DELETE FROM " + canalMsg.getTableName() + " WHERE ";
                StringBuffer idKey = new StringBuffer();
                StringBuffer idValue = new StringBuffer();
                for (CanalEntry.Column c : rowData.getBeforeColumnsList()) {
                    if (c.getIsKey()) {
                        idKey.append(c.getName());
                        idValue.append(c.getValue());
                        break;
                    }


                }

                sql += idKey + " =" + idValue + ";";
                System.out.println(sql);
            }

        }
    }
}

```

- 接口方式

```java
public class EventListenerImpl extends DealCanalEventListener {
	
	@Autowired
	public MyEventListenerimpl(@Qualifier("realInsertOptoin") InsertOption insertOption, 
                               @Qualifier("realDeleteOption") DeleteOption deleteOption, 
                               @Qualifier("realUpdateOption") UpdateOption updateOption) {
		super(insertOption, deleteOption, updateOption);
	}
	
}
```

[参考资料](https://github.com/wanwujiedao/spring-boot-starter-canal)