package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalField;

import java.lang.annotation.Annotation;

public class ClassThatImplementsPositionalAnnotation implements PositionalField {


    @Override
    public int length() {
        return 10;
    }

    @Override
    public String pattern() {
        return "dd/MM/yyyy";
    }

    @Override
    public boolean trim() {
        return true;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return PositionalField.class;
    }
}
