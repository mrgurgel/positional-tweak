package dev.legrug.positionalparser.converter;

import dev.legrug.positionalparser.parser.PositionalInfo;

public interface Converter<TYPE>
{
    TYPE fromPositional(String input, PositionalInfo positionalInfo);
}
