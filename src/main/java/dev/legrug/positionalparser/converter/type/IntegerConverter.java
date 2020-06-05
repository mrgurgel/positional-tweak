package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.vo.PositionalDataVO;

/**
 * Generic number conversor
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer fromPositional(String input, PositionalDataVO positionalDataVO)
    {
        return Integer.valueOf(input);
    }

}
