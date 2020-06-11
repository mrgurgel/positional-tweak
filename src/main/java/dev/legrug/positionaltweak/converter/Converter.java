package dev.legrug.positionaltweak.converter;

import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

public interface Converter<TYPE>
{
    TYPE fromPositional(String input, PositionalFieldVO positionalFieldVO);

    String toPositional(Object pojoFieldValue, PositionalFieldVO positionalFieldVO);
}
