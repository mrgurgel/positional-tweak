package dev.legrug.positionalparser.converter;

import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.exception.PositionalParserException;

public interface Converter<TYPE>
{
    TYPE convert(String input, PositionalData annotationData) throws PositionalParserException;
}
