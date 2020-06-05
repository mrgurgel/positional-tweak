package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalField;

public class PojoWithAFieldThatDoesntExists {

    @PositionalField(length = 1)
    private byte[] aNonConvertableField;

}
