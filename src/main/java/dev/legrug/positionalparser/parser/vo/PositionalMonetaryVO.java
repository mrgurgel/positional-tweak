package dev.legrug.positionalparser.parser.vo;

import dev.legrug.positionalparser.annotation.PositionalMonetary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PositionalMonetaryVO {

    private int numberOfDecimalPlaces;
    private String monetaryPattern;

    public static PositionalMonetaryVO fromAnnotaion(PositionalMonetary positionalMonetary) {
        return new PositionalMonetaryVO(positionalMonetary.numberOfDecimalPlaces(), positionalMonetary.monetaryPattern());
    }
}
