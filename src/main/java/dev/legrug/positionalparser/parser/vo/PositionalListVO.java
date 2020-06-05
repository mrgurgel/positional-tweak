package dev.legrug.positionalparser.parser.vo;

import dev.legrug.positionalparser.annotation.PositionalList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class PositionalListVO {

    private int occurrences;

    public static PositionalListVO fromAnnotaion(PositionalList positionalList) {
        return new PositionalListVO(
                positionalList.occurrences());

    }
}
