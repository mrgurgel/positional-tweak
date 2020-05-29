package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalData;

import java.lang.annotation.Annotation;

public class PositionalDataFieldMock implements PositionalData {



    @Override
    public int length() {
        return 0;
    }

    @Override
    public int precision() {
        return 0;
    }

    @Override
    public String pattern() {
        return null;
    }

    @Override
    public boolean trim() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return PositionalData.class;
    }
}
