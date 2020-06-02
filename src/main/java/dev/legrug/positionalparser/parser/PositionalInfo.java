package dev.legrug.positionalparser.parser;


import dev.legrug.positionalparser.annotation.PositionalData;
import lombok.Getter;

@Getter
public class PositionalInfo {

    private int length;
    private int precision;
    private String pattern;
    boolean trim;

    private PositionalInfo(int length, int precision, String pattern, boolean trim) {
        this.length = length;
        this.precision = precision;
        this.pattern = pattern;
        this.trim = trim;
    }

    public static PositionalInfo fromAnnotaion(PositionalData positionalData) {
        return new PositionalInfo(positionalData.length(),
                positionalData.precision(), positionalData.pattern(), positionalData.trim());

    }
}
