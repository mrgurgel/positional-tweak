package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import dev.legrug.positionaltweak.util.PaddingGenerator;

public class LongConverter implements Converter<Long> {

    @Override
    public Long fromPositional(String input, PositionalFieldVO positionalFieldVO) {
        return Long.valueOf(input.trim());
    }

    @Override
    public String toPositional(Long pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        if(pojoFieldValue == null) {
            pojoFieldValue = 0L;
        }
        return PaddingGenerator.generateZeros(positionalFieldVO,pojoFieldValue.toString()) + pojoFieldValue;
    }
}
