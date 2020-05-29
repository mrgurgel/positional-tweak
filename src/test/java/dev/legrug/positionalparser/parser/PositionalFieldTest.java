package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.exception.PositionalParserException;
import dev.legrug.positionalparser.pojo.FlatPojo;
import dev.legrug.positionalparser.pojo.PojoWithAFieldThatDoesntExists;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class PositionalFieldTest {


    @Test
    public void convertAField() throws NoSuchFieldException {
        Field firstName = FlatPojo.class.getDeclaredField("firstName");
        PositionalField positionalField = new PositionalField(firstName, "Márcio Ribeiro Gurgel do Amaral");
        Object result = positionalField.extractPositionalValue();
        Assert.assertEquals("Márcio Rib", result.toString());
    }

    @Test(expected = PositionalParserException.class)
    public void convertAFieldThatDoesntExists() throws NoSuchFieldException {
        Field fieldThatDoesntHaveAConversor = PojoWithAFieldThatDoesntExists.class.getDeclaredField("aNonConvertableField");
        PositionalField positionalField = new PositionalField(fieldThatDoesntHaveAConversor, "XXXXXX");
        positionalField.extractPositionalValue();
    }

}