package dev.legrug.positionalparser.parser.vo;


import dev.legrug.positionalparser.annotation.PositionalField;
import lombok.Getter;

@Getter
public class PositionalFieldVO {

    private int length;
    private String pattern;
    boolean trim;

    private PositionalFieldVO(int length, String pattern, boolean trim) {
        this.length = length;
        this.pattern = pattern;
        this.trim = trim;
    }

    public static PositionalFieldVO fromAnnotaion(PositionalField positionalField) {
        return new PositionalFieldVO(positionalField.length(),
                positionalField.pattern(), positionalField.trim());

    }
}
