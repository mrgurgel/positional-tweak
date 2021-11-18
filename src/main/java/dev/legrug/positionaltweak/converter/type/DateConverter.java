package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<Date> {

    @Override public Date fromPositional(String input, PositionalFieldVO positionalFieldVO)
    {
        checkIfThereIsPattern(positionalFieldVO);
        try
        {
            return new SimpleDateFormat(positionalFieldVO.getPattern()).parse(input);
        }
        catch (ParseException e)
        {
           throw new PositionalTweakException("There was an error converting de date value: {0}", e, input);
        }
    }

    @Override public String toPositional(Date pojoFieldValue, PositionalFieldVO positionalFieldVO)
    {
        checkIfThereIsPattern(positionalFieldVO);
        return new SimpleDateFormat(positionalFieldVO.getPattern()).format(new Date());
    }

    private void checkIfThereIsPattern(PositionalFieldVO positionalFieldVO)
    {
        if(positionalFieldVO.getPattern() == null)
        {
            throw new PositionalTweakException("One or more date attributes has no pattern");
        }
    }
}
