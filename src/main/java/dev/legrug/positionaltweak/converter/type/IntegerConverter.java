package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import dev.legrug.positionaltweak.util.PaddingGenerator;

/**
 * Generic number conversor
 * @author Marcio Gurgel (marcio.rga@gmail.com)
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
        if(pojoFieldValue == null) {
            pojoFieldValue = 0;
        }
        String value = PaddingGenerator.generateZeros(positionalFieldVO, pojoFieldValue.toString()) + pojoFieldValue;
        SizeChecker.checkSizes(value, positionalFieldVO);

        return value;
    }

}
