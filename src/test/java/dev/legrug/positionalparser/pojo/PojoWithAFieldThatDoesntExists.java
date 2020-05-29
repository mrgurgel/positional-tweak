package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalData;

public class PojoWithAFieldThatDoesntExists {

    @PositionalData(length = 1)
    private byte[] aNonConvertableField;

}
