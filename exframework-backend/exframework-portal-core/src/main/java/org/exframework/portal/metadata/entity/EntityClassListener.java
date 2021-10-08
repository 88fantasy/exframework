package org.exframework.portal.metadata.entity;

import org.exframework.portal.dao.PortalCoreDataItemDao;
import org.exframework.portal.dao.PortalCoreHovDao;
import org.exframework.portal.dao.PortalCoreModuleDao;
import org.exframework.portal.enums.DataItemDisplayType;
import org.exframework.portal.enums.DataItemValueType;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.di.DataItemEntity;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.metadata.hov.HovEntity;
import org.exframework.portal.metadata.hov.IHovDao;
import org.exframework.portal.pub.PageRequest;
import org.exframework.portal.service.sys.PortalCoreDataItemService;
import org.exframework.portal.service.sys.PortalCoreDdlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 加载EntityClass注解
 *
 * @author rwe
 * @since Jan 11, 2021
 * <p>
 * Copyright @ 2021
 */
@Repository
public class EntityClassListener implements ApplicationListener<ApplicationReadyEvent> {

    private Logger log = LoggerFactory.getLogger(EntityClassListener.class.getName());

    @Autowired
    PortalCoreDdlService portalCoreDdlService;

    @Autowired
    PortalCoreDataItemDao portalCoreDataItemDao;

    @Autowired
    PortalCoreHovDao portalCoreHovDao;

    @Autowired
    PortalCoreDataItemService portalCoreDataItemService;

    @Autowired
    PortalCoreModuleDao moduleDao;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext ac = event.getApplicationContext();
        log.info("开始扫描注解" + EntityScan.class.getName());
        Map<String, Object> entities = ac.getBeansWithAnnotation(EntityClass.class);
        for (String dn : entities.keySet()) {
            Object entityClass = entities.get(dn);
            Class<? extends Object> clazz = entityClass.getClass();
            log.info(MessageFormat.format("正在检查{0}", entityClass.getClass().getName()));
            ReflectionUtils.doWithFields(entityClass.getClass(), new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    Object value = field.get(entityClass);
                    try {
                        // 如果字段添加了我们自定义注解
                        if (field.isAnnotationPresent(DataItemEntity.class)) {
                            log.info(MessageFormat.format("正在检查数据项{0}", field.getName()));
                            DataItemEntity item = field.getAnnotation(DataItemEntity.class);
                            String code = item.value();
                            String name = item.name();
                            String description = item.description();
                            DataItemDisplayType type = item.type();
                            DataItemValueType valueType = item.valueType();
                            String displayKey = item.displayKey();
                            int length = item.maxlength();
                            int precision = item.precision();
                            String objectCode = item.objectCode();
                            boolean forceUpdate = item.forceUpdate();

                            if (valueType == DataItemValueType.DEFAULT) {
                                valueType = portalCoreDataItemDao.defaultValueType(value);
                            }

                            if (!StringUtils.hasText(objectCode)) {
                                DataItem dataitem = portalCoreDataItemDao.findByKey(code);
                                if (dataitem == null) {
                                    dataitem = new DataItem(code, name, description, type, displayKey, valueType, length, precision);
                                    portalCoreDataItemDao.insert(dataitem);
                                } else if (forceUpdate) {
                                    dataitem = new DataItem(code, name, description, type, displayKey, valueType, length, precision);
                                    portalCoreDataItemDao.update(dataitem);
                                }
                            }
                        }
                    } catch (Exception e) {
                        log.error(MessageFormat.format("设置配置项[{0}]失败: {1}", field.getName(), e.getMessage()), e);
                    }
                }
            });

            HovEntity hov = entityClass.getClass().getAnnotation(HovEntity.class);
            if (hov != null) {
                String code = hov.value();
                String name = hov.name();
                String description = hov.description();
                Class<? extends PageRequest> request = hov.requestEntity();
                Class<? extends IHovDao<?>> daoClass = hov.hovDao();
                String returnKey = hov.returnKey();
                boolean force = hov.forceUpdate();
                Hov entity = portalCoreHovDao.findByKey(code);
                if (entity == null) {
                    entity = Hov.instanceByClass(portalCoreDataItemService, code, name, description, request, daoClass, returnKey);
                    portalCoreHovDao.insert(entity);
                } else if (force) {
                    entity = Hov.instanceByClass(portalCoreDataItemService, code, name, description, request, daoClass, returnKey);
                    portalCoreHovDao.update(entity);
                }
            }

//            ModuleEntity module = entityClass.getClass().getAnnotation(ModuleEntity.class);
//            if (module != null) {
//                String code = module.value();
//                String name = module.name();
//                String description = module.description();
//                String[] items = module.dataItemRef();
//                String[] hovs = module.hovRef();
//                String[] permissions = module.permissionRef();
//                boolean force = module.forceUpdate();
//                ModuleBase base = moduleDao.findByKey(code);
//                if (base == null) {
//                    base = new ModuleBase(code, name, description);
//                    Module entity = moduleService.instanceByClass(base, items, hovs, permissions);
//                    moduleDao.insert(entity);
//                } else if (force) {
//                    Module entity = moduleService.instanceByClass(base, items, hovs, permissions);
//                    moduleDao.update(entity);
//                }
//            }

        }
    }
}
