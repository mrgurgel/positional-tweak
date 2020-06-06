package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;

public class PojoWithAFieldThatDoesntExists {

    @PositionalField(length = 1)
    private byte[] aNonConvertableField;

}
