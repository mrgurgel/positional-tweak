package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.exception.PositionalParserException;
import dev.legrug.positionalparser.parser.PositionalInfo;

/**
 * Converter for annotated attributes which has <i>String</i> type
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class StringConverter implements Converter<String>
{
    /**
     * (non-Javadoc)
     * @see Converter#fromPositional(String, PositionalInfo)
     */
    @Override
    public String fromPositional(String input, PositionalInfo positionalInfo)
    {
        return input;
    }

}
