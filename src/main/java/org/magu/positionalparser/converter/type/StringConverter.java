package org.magu.positionalparser.converter.type;

import org.magu.positionalparser.annotation.PositionalData;
import org.magu.positionalparser.converter.Converter;
import org.magu.positionalparser.exception.PositionalParserException;

/**
 * Converter for annotated attributes which has <i>String</i> type
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class StringConverter implements Converter<String> 
{
        
    /**
     * (non-Javadoc)
     * @see org.magu.positionalparser.converter.Converter#convert(java.lang.String, org.magu.positionalparser.annotation.PositionalData)
     */
    @Override
    public String convert(String input, PositionalData annotationData)
            throws PositionalParserException 
    {
        // TODO: apply treatment
        return input;
    }

}
