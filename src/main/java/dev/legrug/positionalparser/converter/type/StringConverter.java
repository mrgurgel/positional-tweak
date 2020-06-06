package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.vo.PositionalFieldVO;

/**
 * Converter for annotated attributes which has <i>String</i> type
 * @author Márcio Gurgel (marcio.rga@gmail.com)
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

}
