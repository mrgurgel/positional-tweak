package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.exception.PositionalParserException;

/**
 * Converter for annotated attributes which has <i>String</i> type
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class StringConverter implements Converter<String>
{
        
    /**
     * (non-Javadoc)
     * @see Converter#convert(java.lang.String, PositionalData)
     */
    @Override
    public String convert(String input, PositionalData annotationData)
            throws PositionalParserException 
    {
        // TODO: apply treatment
        return input;
    }

}
