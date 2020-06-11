package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

public class LongConverter implements Converter<Long> {

    @Override
    public Long fromPositional(String input, PositionalFieldVO positionalFieldVO) {
        return Long.valueOf(input.trim());
    }

    @Override
    public String toPositional(Object pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        return pojoFieldValue.toString();
    }
}
