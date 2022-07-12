package org.exframework.spring.boot.canal.client.transfer;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.annotation.ListenPoint;
import org.exframework.spring.boot.canal.client.abstracts.AbstractBasicMessageTransponder;
import org.exframework.spring.boot.canal.client.core.CanalMsg;
import org.exframework.spring.boot.canal.client.core.ListenerPoint;
import org.exframework.spring.boot.canal.client.interfaces.CanalEventListener;
import org.exframework.spring.boot.canal.config.CanalProperties;
import org.exframework.support.common.util.SpringContextUtils;
import org.springframework.core.env.Environment;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 默認的信息轉換器
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public class DefaultMessageTransponder extends AbstractBasicMessageTransponder {


    public DefaultMessageTransponder(CanalConnector connector, Map.Entry<String, CanalProperties.Instance> config, List<CanalEventListener> listeners, List<ListenerPoint> annoListeners) {
        super(connector, config, listeners, annoListeners);
    }


    /**
     * 断言注解方式的监听过滤规则
     *
     * @param destination 指定
     * @param schemaName  数据库实例
     * @param tableName   表名称
     * @param eventType   事件类型
     * @return
     */
    @Override
    protected Predicate<Map.Entry<Method, ListenPoint>> getAnnotationFilter(String destination, String schemaName, String tableName, CanalEntry.EventType eventType) {

        Environment env = SpringContextUtils.getBeanByClass(Environment.class);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("env", env);
        //看看指令是否正确
        Predicate<Map.Entry<Method, ListenPoint>> df = e -> StringUtils.hasLength(e.getValue().destination())
                || new SpelExpressionParser().parseExpression(e.getValue().destination()).getValue(ctx, String.class).equals(destination) || destination == null;

        //看看数据库实例名是否一样
        Predicate<Map.Entry<Method, ListenPoint>> sf = e -> e.getValue().database().length == 0
                || Arrays.stream(e.getValue().database()).anyMatch(s -> s.equals(schemaName)) || schemaName == null;

        //看看表名是否一样
        Predicate<Map.Entry<Method, ListenPoint>> tf = e -> e.getValue().table().length == 0
                || Arrays.stream(e.getValue().table()).anyMatch(t -> t.equals(tableName)) || tableName == null;

        //类型一致？
        Predicate<Map.Entry<Method, ListenPoint>> ef = e -> e.getValue().eventType().length == 0
                || Arrays.stream(e.getValue().eventType()).anyMatch(ev -> ev == eventType) || eventType == null;

        return df.and(sf).and(tf).and(ef);
    }

    /**
     * 获取处理的参数
     *
     * @param method    监听的方法
     * @param canalMsg  事件节点
     * @param rowChange 詳細參數
     * @return
     */
    @Override
    protected Object[] getInvokeArgs(Method method, CanalMsg canalMsg, CanalEntry.RowChange rowChange) {
        return Arrays.stream(method.getParameterTypes())
                .map(p -> p == CanalMsg.class ? canalMsg : p == CanalEntry.RowChange.class ? rowChange : null)
                .toArray();
    }


    /**
     * 忽略实体类的类型
     *
     * @param
     * @return
     */
    @Override
    protected List<CanalEntry.EntryType> getIgnoreEntryTypes() {
        return Arrays.asList(CanalEntry.EntryType.TRANSACTIONBEGIN, CanalEntry.EntryType.TRANSACTIONEND, CanalEntry.EntryType.HEARTBEAT);
    }
}
