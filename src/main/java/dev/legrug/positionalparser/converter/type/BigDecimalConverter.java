package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.vo.PositionalDataVO;

import java.math.BigDecimal;

public class BigDecimalConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal fromPositional(String input, PositionalDataVO positionalDataVO) {
        String inputWithoutZerosOnLeft = input.replaceAll("^0+(?!$)", "");
        int numberOfDecimalPlaces = positionalDataVO.getPositionalMonetaryVO().getNumberOfDecimalPlaces();
        if(numberOfDecimalPlaces != 0) {
            String valueWithSeparator = inputWithoutZerosOnLeft
                    .replaceAll("^(.{" + numberOfDecimalPlaces + "})", "$1" + ".");
            return new BigDecimal(valueWithSeparator);
        }
        return new BigDecimal(inputWithoutZerosOnLeft);
    }


}
