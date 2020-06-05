package dev.legrug.positionalparser.converter.type;

import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.parser.vo.PositionalDataVO;

/**
 * Converter for annotated attributes which has <i>String</i> type
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class StringConverter implements Converter<String>
{
    /**
     * (non-Javadoc)
     * @see Converter#fromPositional(String, PositionalDataVO)
     */
    @Override
    public String fromPositional(String input, PositionalDataVO positionalDataVO)
    {
        return positionalDataVO.getPositionalFieldVO().isTrim() ? input.trim() : input;
    }

}
