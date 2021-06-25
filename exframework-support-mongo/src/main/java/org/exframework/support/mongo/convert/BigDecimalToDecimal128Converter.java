package org.exframework.support.mongo.convert;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import java.math.BigDecimal;

/**
* @author rwe
* @version 创建时间：Jun 18, 2020 10:06:37 AM
* java-->mongo  即BigDecimal变为Decimal128的转换器
*/

@WritingConverter
public class BigDecimalToDecimal128Converter implements Converter<BigDecimal, Decimal128> {
    @Override
    public Decimal128 convert(BigDecimal bigDecimal) {
        return new Decimal128(bigDecimal);
    }
}
