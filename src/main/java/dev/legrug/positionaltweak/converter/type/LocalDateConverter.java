package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements Converter<LocalDate> {

    @Override public LocalDate fromPositional(String input, PositionalFieldVO positionalFieldVO)
    {
        checkIfThereIsPattern(positionalFieldVO);
        return LocalDate.parse(input, DateTimeFormatter.ofPattern(positionalFieldVO.getPattern()));
    }

    @Override public String toPositional(LocalDate pojoFieldValue, PositionalFieldVO positionalFieldVO)
    {
        checkIfThereIsPattern(positionalFieldVO);
        String value = DateTimeFormatter.ofPattern(positionalFieldVO.getPattern()).format(pojoFieldValue);
        SizeChecker.checkSizes(value, positionalFieldVO);

        return value;
    }

    private void checkIfThereIsPattern(PositionalFieldVO positionalFieldVO)
    {
        if(positionalFieldVO.getPattern() == null)
        {
            throw new PositionalTweakException("One or more date attributes has no pattern");
        }
    }
}
