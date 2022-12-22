package dev.legrug.positionaltweak.util;

import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaddingGenerator {

    public static String generateBlankSpaces(PositionalFieldVO positionalFieldVO, String rawPojoValueAsString) {
        return Stream.generate(() -> " ").limit(identifyNumberOfZerosBefore(positionalFieldVO, rawPojoValueAsString))
                .collect(Collectors.joining());
    }

    public static String generateZeros(PositionalFieldVO positionalFieldVO, String rawPojoValueAsString) {
        return Stream.generate(() -> "0").limit(identifyNumberOfZerosBefore(positionalFieldVO, rawPojoValueAsString))
                .collect(Collectors.joining());
    }

    private static int identifyNumberOfZerosBefore(PositionalFieldVO positionalFieldVO, String rawPojoValueAsString) {
        int numberOfCharactersUsedByTheNumber = rawPojoValueAsString.length();
        int numberOfZerosBefore = positionalFieldVO.getLength() - numberOfCharactersUsedByTheNumber;
        return numberOfZerosBefore > 0 ? numberOfZerosBefore : 0;
    }

    private static int countDecimalSeparatorCaseItExists(PositionalFieldVO positionalFieldVO) {
        String decimalSeparator = positionalFieldVO.getPositionalMonetaryVO().getDecimalSeparator();
        if(decimalSeparator.equals(".")) {
            return 0;
        }
        return decimalSeparator.length();
    }
}
