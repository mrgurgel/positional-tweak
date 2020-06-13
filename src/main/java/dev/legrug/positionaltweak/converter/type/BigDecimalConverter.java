package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import dev.legrug.positionaltweak.parser.vo.PositionalMonetaryVO;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigDecimalConverter implements Converter<BigDecimal> {

    public static final int DECIMAL_NOT_INFORMED = -1;
    public static final String REGEX_THAT_THREATS_ZERO_ON_LEFT = "^0+(?!$)";
    public static final String DEFAULT_DECIMA_SEPARATOR_WHEN_TO_STRING = ".";

    @Override
    public BigDecimal fromPositional(String input, PositionalFieldVO positionalFieldVO) {
        PositionalMonetaryVO positionalMonetaryVO = positionalFieldVO.getPositionalMonetaryVO();
        String rawInput = input
                .replaceAll(REGEX_THAT_THREATS_ZERO_ON_LEFT, "")
                .replace(positionalMonetaryVO.getDecimalSeparator(), "");

        int numberOfDecimalPlaces = positionalMonetaryVO.getNumberOfDecimalPlaces();
        if (numberOfDecimalPlaces != DECIMAL_NOT_INFORMED) {
            String valueWithSeparator = insertDotAsDecimalSeparator(rawInput, numberOfDecimalPlaces);
            return new BigDecimal(valueWithSeparator);
        }
        return new BigDecimal(rawInput);
    }

    private String insertDotAsDecimalSeparator(String inputWithoutZerosOnLeft, int numberOfDecimalPlaces) {
        return inputWithoutZerosOnLeft
                .replaceAll("^(.{" + numberOfDecimalPlaces + "})", "$1" + ".");
    }

    @Override
    public String toPositional(BigDecimal pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        String rawPojoValueAsString = pojoFieldValue.toString()
                .replace(DEFAULT_DECIMA_SEPARATOR_WHEN_TO_STRING, positionalFieldVO.getPositionalMonetaryVO().getDecimalSeparator());
        String correctNumberOfZeros = Stream.generate(() -> "0").limit(identifyNumberOfZerosBefore(positionalFieldVO, rawPojoValueAsString))
                .collect(Collectors.joining());
        return correctNumberOfZeros + rawPojoValueAsString;
    }

    private int identifyNumberOfZerosBefore(PositionalFieldVO positionalFieldVO, String rawPojoValueAsString) {
        int length = countDecimalSeparatorIfNotDotOrEpty(positionalFieldVO);
        int numberOfCharactersUsedByTheNumber = rawPojoValueAsString.length() + length;
        int numberOfZerosBefore = positionalFieldVO.getLength() - numberOfCharactersUsedByTheNumber;
        return numberOfZerosBefore > 0 ? numberOfZerosBefore : 0;
    }

    private int countDecimalSeparatorIfNotDotOrEpty(PositionalFieldVO positionalFieldVO) {
        String decimalSeparator = positionalFieldVO.getPositionalMonetaryVO().getDecimalSeparator();
        if(decimalSeparator.equals(".")) {
            return 0;
        }
        return decimalSeparator.length();
    }

}
