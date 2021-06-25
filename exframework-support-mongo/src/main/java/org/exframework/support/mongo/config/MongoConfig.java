package org.exframework.support.mongo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import org.exframework.support.mongo.convert.BigDecimalToDecimal128Converter;
import org.exframework.support.mongo.convert.Decimal128ToBigDecimalConverter;

import java.util.ArrayList;
import java.util.List;

/**
* @author rwe
* @version 创建时间：Jun 18, 2020 10:08:20 AM
* mongo 配置
*/

@Configuration
public class MongoConfig {

	@Bean
	@ConditionalOnMissingBean(name = "mongoCustomConversions")
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new BigDecimalToDecimal128Converter());
        converterList.add(new Decimal128ToBigDecimalConverter());
        return new MongoCustomConversions(converterList);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory factory){
        return new MongoTransactionManager(factory);
    }
}
