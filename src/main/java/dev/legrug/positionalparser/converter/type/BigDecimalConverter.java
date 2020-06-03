package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.PositionalInfo;

import java.math.BigDecimal;

public class BigDecimalConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal fromPositional(String input, PositionalInfo positionalInfo) {
        String inputWithoutZerosOnLeft = input.replaceAll("^0+(?!$)", "");
        String valueWithSeparator = inputWithoutZerosOnLeft.replaceAll("^(.{" + positionalInfo.getPrecision() + "})", "$1" + ".");
        BigDecimal bigDecimal = new BigDecimal(valueWithSeparator);
        return bigDecimal;
    }


}
