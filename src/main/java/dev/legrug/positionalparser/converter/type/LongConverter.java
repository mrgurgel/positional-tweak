package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.PositionalInfo;

public class LongConverter implements Converter<Long> {

    @Override
    public Long fromPositional(String input, PositionalInfo positionalInfo) {
        return Long.valueOf(input.trim());
    }
}
