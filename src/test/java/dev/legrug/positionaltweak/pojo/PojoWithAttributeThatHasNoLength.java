package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PojoWithAttributeThatHasNoLength {

    @PositionalField
    private String primitiveFieldWithNoLenght;
}
