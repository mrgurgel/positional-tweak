package org.magu.positionalparser.converter.type;

import org.magu.positionalparser.annotation.PositionalData;
import org.magu.positionalparser.converter.Converter;
import org.magu.positionalparser.exception.PositionalParserException;

/**
 * Generic number conversor
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer convert(String input, PositionalData annotationData)
            throws PositionalParserException 
    {
        return Integer.valueOf(input);
    }

}
