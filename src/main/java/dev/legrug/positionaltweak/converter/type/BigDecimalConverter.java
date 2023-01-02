package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import dev.legrug.positionaltweak.parser.vo.PositionalMonetaryVO;
import dev.legrug.positionaltweak.util.PaddingGenerator;

import java.math.BigDecimal;

public class BigDecimalConverter implements Converter<BigDecimal> {

    public static final int DECIMAL_NOT_INFORMED = -1;
    public static final String DEFAULT_DECIMA_SEPARATOR_WHEN_TO_STRING = ".";

    @Override
    public BigDecimal fromPositional(String rawInput, PositionalFieldVO positionalFieldVO) {
        PositionalMonetaryVO positionalMonetaryVO = positionalFieldVO.getPositionalMonetaryVO();

        int numberOfDecimalPlaces = positionalMonetaryVO.getNumberOfDecimalPlaces();
        if (numberOfDecimalPlaces != DECIMAL_NOT_INFORMED) {
            if (positionalMonetaryVO.getDecimalSeparator().equals(".")) {
                rawInput = rawInput.replaceAll("\\.", ""); // and we remove any other character
            } else {
                rawInput = rawInput.replace((positionalMonetaryVO.getDecimalSeparator()), ""); // and we remove any other character
            }

            String valueWithSeparator = insertDotAsDecimalSeparator(positionalMonetaryVO, rawInput,
                    identifyPositionToInsertTheDot(rawInput, numberOfDecimalPlaces));
            return new BigDecimal(valueWithSeparator);
        }
        return new BigDecimal(rawInput);
    }

    private int identifyPositionToInsertTheDot(String rawInput, int numberOfDecimalPlaces) {
        return Math.abs(numberOfDecimalPlaces - rawInput.length());
    }

    private String insertDotAsDecimalSeparator(PositionalMonetaryVO positionalMonetaryVO, String inputWithoutZerosOnLeft, int positionToSetTheDot) {

        return inputWithoutZerosOnLeft
                .replaceAll("^(.{" + positionToSetTheDot + "})", "$1" + ".");
    }

    @Override
    public String toPositional(BigDecimal pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        if(pojoFieldValue == null) {
            pojoFieldValue = BigDecimal.ZERO;
        }
        String rawPojoValueAsString = pojoFieldValue.setScale(positionalFieldVO.getPositionalMonetaryVO().getNumberOfDecimalPlaces()).toPlainString()
                .replace(DEFAULT_DECIMA_SEPARATOR_WHEN_TO_STRING, positionalFieldVO.getPositionalMonetaryVO().getDecimalSeparator());
        String numberOfZerosBefore = PaddingGenerator.generateZeros(positionalFieldVO, rawPojoValueAsString);
        String value = numberOfZerosBefore + rawPojoValueAsString;
        SizeChecker.checkSizes(value, positionalFieldVO);

        return value;
    }


}
