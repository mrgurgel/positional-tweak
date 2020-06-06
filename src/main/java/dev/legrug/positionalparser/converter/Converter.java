package dev.legrug.positionalparser.converter;

import dev.legrug.positionalparser.parser.vo.PositionalFieldVO;

public interface Converter<TYPE>
{
    TYPE fromPositional(String input, PositionalFieldVO positionalFieldVO);
}
