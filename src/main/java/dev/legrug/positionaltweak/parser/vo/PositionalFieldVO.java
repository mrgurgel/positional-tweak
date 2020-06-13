package dev.legrug.positionaltweak.parser.vo;


import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalList;
import dev.legrug.positionaltweak.annotation.PositionalMonetary;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PositionalFieldVO {

    private PositionalListVO positionalListVO;
    private PositionalMonetaryVO positionalMonetaryVO;

    private int length;
    private String pattern;
    boolean trim;


    public PositionalFieldVO(PositionalField positionalField) {
        if(positionalField != null)
        {
            this.length = positionalField.length();
            this.pattern = positionalField.pattern();
            this.trim = positionalField.trim();
            extractListInfoIfAny(positionalField);
            extractMonetaryInfoIfAny(positionalField);
        }
    }

    private void extractListInfoIfAny(PositionalField positionalField) {
        PositionalList positionalList = positionalField.listInfo();
        if(positionalList != null)
        {
            positionalListVO = PositionalListVO.fromAnnotaion(positionalList);
        }
    }

    private void extractMonetaryInfoIfAny(PositionalField positionalField) {
        PositionalMonetary positionalMonetary = positionalField.monetaryInfo();
        if(positionalMonetary != null)
        {
            positionalMonetaryVO = PositionalMonetaryVO.fromAnnotaion(positionalMonetary);
        }
    }

}
