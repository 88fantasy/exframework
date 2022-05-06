package org.exframework.support.jdbc.annotation;

import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.support.common.annotation.AnnotationClassPathBeanDefinitionScanner;
import org.exframework.support.common.annotation.ScannerRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据库表扫描
 * @author rwe Date: Jan 9, 2021
 *
 * Copyright @ 2021
 * 
 */
public class TableScannerRegistrar extends ScannerRegistrar<TableEntityScan, TableName> {


}
