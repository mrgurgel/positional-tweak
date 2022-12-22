package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import dev.legrug.positionaltweak.util.PaddingGenerator;

/**
 * Converter for annotated attributes which has <i>String</i> type
 * @author Marcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class StringConverter implements Converter<String>
{
    /**
     * (non-Javadoc)
     * @see Converter#fromPositional(String, PositionalFieldVO)
     */
    @Override
    public String fromPositional(String input, PositionalFieldVO positionalFieldVO)
    {
        return positionalFieldVO.isTrim() ? input.trim() : input;
    }

    @Override
    public String toPositional(String pojoFieldValue, PositionalFieldVO positionalFieldVO) {
        return pojoFieldValue + PaddingGenerator.generateBlankSpaces(positionalFieldVO, pojoFieldValue);
    }

}
