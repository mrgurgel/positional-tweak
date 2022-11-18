package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    @Override public LocalDateTime fromPositional(String input, PositionalFieldVO positionalFieldVO)
    {
        checkIfThereIsPattern(positionalFieldVO);
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(positionalFieldVO.getPattern()));
    }

    @Override public String toPositional(LocalDateTime pojoFieldValue, PositionalFieldVO positionalFieldVO)
    {
        checkIfThereIsPattern(positionalFieldVO);
        return DateTimeFormatter.ofPattern(positionalFieldVO.getPattern()).format(pojoFieldValue);
    }

    private void checkIfThereIsPattern(PositionalFieldVO positionalFieldVO)
    {
        if(positionalFieldVO.getPattern() == null)
        {
            throw new PositionalTweakException("One or more date attributes has no pattern");
        }
    }
}
