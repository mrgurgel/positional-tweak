package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.exception.PositionalParserException;

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
