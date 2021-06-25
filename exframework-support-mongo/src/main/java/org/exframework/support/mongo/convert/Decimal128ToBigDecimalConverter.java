package org.exframework.support.mongo.convert;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import java.math.BigDecimal;
	
/**
* @author rwe
* @version 创建时间：Jun 18, 2020 10:07:40 AM
* mongo--->java  即Decimal128变为BigDecimal的转换器
*/

@ReadingConverter
public class Decimal128ToBigDecimalConverter implements Converter<Decimal128, BigDecimal> {
    @Override
    public BigDecimal convert(Decimal128 decimal128) {
        return decimal128.bigDecimalValue();
    }
}
