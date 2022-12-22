package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import dev.legrug.positionaltweak.parser.vo.PositionalMonetaryVO;
import dev.legrug.positionaltweak.util.PaddingGenerator;

import java.math.BigDecimal;

public class BigDecimalConverter implements Converter<BigDecimal> {

    public static final int DECIMAL_NOT_INFORMED = -1;
    public static final String REGEX_THAT_THREATS_ZERO_ON_LEFT = "^0+(?!$)";
    public static final String DEFAULT_DECIMA_SEPARATOR_WHEN_TO_STRING = ".";
    public static final int VALUE_IS_DECIMAL_ONLY = 0;

    @Override
    public BigDecimal fromPositional(String rawInput, PositionalFieldVO positionalFieldVO) {
        PositionalMonetaryVO positionalMonetaryVO = positionalFieldVO.getPositionalMonetaryVO();
//        String rawInput = input
//                .replaceAll(REGEX_THAT_THREATS_ZERO_ON_LEFT, "");


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
//        if (rawInput.length() <= numberOfDecimalPlaces) {
//            return VALUE_IS_DECIMAL_ONLY;
//        }
        return Math.abs(numberOfDecimalPlaces - rawInput.length());
    }

    private String insertDotAsDecimalSeparator(PositionalMonetaryVO positionalMonetaryVO, String inputWithoutZerosOnLeft, int positionToSetTheDot) {

        return inputWithoutZerosOnLeft
                .replaceAll("^(.{" + positionToSetTheDot + "})", "$1" + ".");
    }

    @Override
    public String toPositional(BigDecimal pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        String rawPojoValueAsString = pojoFieldValue.setScale(positionalFieldVO.getPositionalMonetaryVO().getNumberOfDecimalPlaces()).toPlainString()
                .replace(DEFAULT_DECIMA_SEPARATOR_WHEN_TO_STRING, positionalFieldVO.getPositionalMonetaryVO().getDecimalSeparator());
        String numberOfZerosBefore = PaddingGenerator.generateZeros(positionalFieldVO, rawPojoValueAsString);
        return numberOfZerosBefore + rawPojoValueAsString;
    }


}
