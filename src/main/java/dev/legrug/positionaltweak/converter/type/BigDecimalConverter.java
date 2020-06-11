package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

import java.math.BigDecimal;

public class BigDecimalConverter implements Converter<BigDecimal> {

    public static final int DECIMAL_NOT_INFORMED = -1;

    @Override
    public BigDecimal fromPositional(String input, PositionalFieldVO positionalFieldVO) {
        String inputWithoutZerosOnLeft = input.replaceAll("^0+(?!$)", "");
        int numberOfDecimalPlaces = positionalFieldVO.getPositionalMonetaryVO().getNumberOfDecimalPlaces();
        if(numberOfDecimalPlaces != DECIMAL_NOT_INFORMED) {
            String valueWithSeparator = inputWithoutZerosOnLeft
                    .replaceAll("^(.{" + numberOfDecimalPlaces + "})", "$1" + ".");
            return new BigDecimal(valueWithSeparator);
        }
        return new BigDecimal(inputWithoutZerosOnLeft);
    }

    @Override
    public String toPositional(Object pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        return null;
    }


}
