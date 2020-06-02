package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.exception.PositionalParserException;
import dev.legrug.positionalparser.parser.PositionalInfo;

/**
 * Generic number conversor
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer fromPositional(String input, PositionalInfo positionalInfo)
    {
        return Integer.valueOf(input);
    }

}
