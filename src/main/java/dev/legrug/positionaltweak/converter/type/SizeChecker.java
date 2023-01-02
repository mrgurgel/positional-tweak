package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

public class SizeChecker {

    public static void checkSizes(String positionalValue, PositionalFieldVO positionalFieldVO) {
        if(positionalValue.length() > positionalFieldVO.getLength()) {
            throw new PositionalTweakException("The value [{0}] is greater than [{1}]", positionalValue, positionalFieldVO.getLength());
        }
    }
}
