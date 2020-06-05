package dev.legrug.positionalparser.converter;

import dev.legrug.positionalparser.parser.vo.PositionalDataVO;

public interface Converter<TYPE>
{
    TYPE fromPositional(String input, PositionalDataVO positionalDataVO);
}
