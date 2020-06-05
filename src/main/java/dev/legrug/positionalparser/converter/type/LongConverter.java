package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.vo.PositionalDataVO;

public class LongConverter implements Converter<Long> {

    @Override
    public Long fromPositional(String input, PositionalDataVO positionalDataVO) {
        return Long.valueOf(input.trim());
    }
}
