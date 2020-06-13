package dev.legrug.positionaltweak.parser.vo;

import dev.legrug.positionaltweak.annotation.PositionalMonetary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PositionalMonetaryVO {

    private int numberOfDecimalPlaces;
    private String monetaryPattern;
    private String decimalSeparator = "";

    public static PositionalMonetaryVO fromAnnotaion(PositionalMonetary positionalMonetary) {
        return new PositionalMonetaryVO(positionalMonetary.numberOfDecimalPlaces(), positionalMonetary.monetaryPattern(),
                positionalMonetary.decimalSeparator());
    }
}
