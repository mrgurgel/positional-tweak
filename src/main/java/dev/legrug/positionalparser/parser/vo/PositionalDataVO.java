package dev.legrug.positionalparser.parser.vo;

import dev.legrug.positionalparser.annotation.PositionalMonetary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PositionalDataVO {

    private PositionalFieldVO positionalFieldVO;
    private PositionalListVO positionalListVO;
    private PositionalMonetaryVO positionalMonetaryVO;

}
