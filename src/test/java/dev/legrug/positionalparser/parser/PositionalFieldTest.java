package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.exception.PositionalParserException;
import dev.legrug.positionalparser.pojo.FlatPojo;
import dev.legrug.positionalparser.pojo.PojoWithList;
import dev.legrug.positionalparser.pojo.PojoWithAFieldThatDoesntExists;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

public class PositionalFieldTest {


    @Test
    public void convertAField() throws NoSuchFieldException {
        Field firstName = FlatPojo.class.getDeclaredField("firstName");
        FlatPojo currentInstance = new FlatPojo();
        new PositionalField(firstName, currentInstance, new StringBuilder("Márcio Ribeiro Gurgel do Amaral")).fillFieldValue();
        Assert.assertEquals("Márcio Rib", currentInstance.getFirstName());
    }


    @Test
    public void confirmThatAFieldIsNotConvertable() throws NoSuchFieldException {

        Field fieldThatDoesntHaveAConversor = PojoWithAFieldThatDoesntExists.class.getDeclaredField("aNonConvertableField");
        PositionalField positionalField = new PositionalField(fieldThatDoesntHaveAConversor, new FlatPojo(), new StringBuilder("XXXXXX"));
        Assert.assertFalse(positionalField.isThisAPrimitiveValue());

    }

    @Test
    public void confirmThatAFieldIsConvertable() throws NoSuchFieldException {
        Field firstName = FlatPojo.class.getDeclaredField("firstName");
        FlatPojo currentInstance = new FlatPojo();
        PositionalField positionalField = new PositionalField(firstName, currentInstance, new StringBuilder("Márcio Ribeiro Gurgel do Amaral"));
        Assert.assertTrue(positionalField.isThisAPrimitiveValue());

    }

    @Test
    public void checkIfNonPositionalFieldsWillBeSkiped() throws NoSuchFieldException {
        Field size = HashMap.class.getDeclaredField("size");
        HashMap currentInstance = new HashMap<>();
        PositionalField positionalField = new PositionalField(size, currentInstance, new StringBuilder("test"));
        Assert.assertFalse(positionalField.isThisAPrimitiveValue());

    }

    @Test
    public void detectAFieldListWithSuccess() throws NoSuchFieldException {
        Field firstName = PojoWithList.class.getDeclaredField("listOfFlatPojos");
        PojoWithList currentInstance = new PojoWithList();
        PositionalField positionalField = new PositionalField(firstName, currentInstance, new StringBuilder("test"));
        Assert.assertTrue(positionalField.isThisFieldAList());
    }

    @Test
    public void convertAComplexList() throws NoSuchFieldException {
        Field firstName = PojoWithList.class.getDeclaredField("listOfFlatPojos");
        PojoWithList currentInstance = new PojoWithList();
        new PositionalField(firstName, currentInstance,
                new StringBuilder("AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFF")).fillFieldValue();
        Assert.assertEquals(3, currentInstance.getListOfFlatPojos().size());
        Assert.assertEquals("FFFFFFFFFF", currentInstance.getListOfFlatPojos().get(2).getLastName());
    }

}