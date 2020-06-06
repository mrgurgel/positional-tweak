package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.vo.PositionalFieldVO;

public class LongConverter implements Converter<Long> {

    @Override
    public Long fromPositional(String input, PositionalFieldVO positionalFieldVO) {
        return Long.valueOf(input.trim());
    }
}
