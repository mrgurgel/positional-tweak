package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

/**
 * Generic number conversor
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer fromPositional(String input, PositionalFieldVO positionalFieldVO)
    {
        return Integer.valueOf(input);
    }

    @Override
    public String toPositional(Integer pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        return pojoFieldValue.toString();
    }

}
