package org.magu.positionalparser.converter;

import org.magu.positionalparser.annotation.PositionalData;
import org.magu.positionalparser.exception.PositionalParserException;

public interface Converter<TYPE>
{
    TYPE convert(String input, PositionalData annotationData) throws PositionalParserException;
}
