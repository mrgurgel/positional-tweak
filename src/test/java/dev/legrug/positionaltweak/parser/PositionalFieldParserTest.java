package dev.legrug.positionaltweak.parser;

import dev.legrug.positionaltweak.pojo.AccountPojoWithAllSupportedPrimitives;
import dev.legrug.positionaltweak.pojo.FlatPojo;
import dev.legrug.positionaltweak.pojo.PojoWithList;
import dev.legrug.positionaltweak.pojo.PojoWithAFieldThatDoesntExists;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;

public class PositionalFieldParserTest {


    @Test
    public void convertAFieldPrimitive() throws NoSuchFieldException {
        Field firstName = FlatPojo.class.getDeclaredField("firstName");
        FlatPojo currentInstance = new FlatPojo();
        new PositionalFieldParser(firstName, currentInstance, new StringBuilder("Márcio Ribeiro Gurgel do Amaral")).fillFieldValue();
        Assert.assertEquals("Márcio Rib", currentInstance.getFirstName());
    }


    @Test
    public void confirmThatANonSupportedFieldIsNotConvertable() throws NoSuchFieldException {

        Field fieldThatDoesntHaveAConversor = PojoWithAFieldThatDoesntExists.class.getDeclaredField("aNonConvertableField");
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(fieldThatDoesntHaveAConversor, new FlatPojo(), new StringBuilder("XXXXXX"));
        Assert.assertFalse(positionalFieldParser.isThisAPrimitiveValue());

    }

    @Test
    public void confirmThatAPrimitiveFieldIsConvertable() throws NoSuchFieldException {
        Field firstName = FlatPojo.class.getDeclaredField("firstName");
        FlatPojo currentInstance = new FlatPojo();
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(firstName, currentInstance, new StringBuilder("Márcio Ribeiro Gurgel do Amaral"));
        Assert.assertTrue(positionalFieldParser.isThisAPrimitiveValue());

    }

    @Test
    public void checkIfNonPositionalFieldsWillBeSkiped() throws NoSuchFieldException {
        Field size = HashMap.class.getDeclaredField("size");
        HashMap currentInstance = new HashMap<>();
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(size, currentInstance, new StringBuilder("test"));
        Assert.assertFalse(positionalFieldParser.isThisAPrimitiveValue());

    }

    @Test
    public void detectAFieldListWithSuccess() throws NoSuchFieldException {
        Field firstName = PojoWithList.class.getDeclaredField("listOfFlatPojos");
        PojoWithList currentInstance = new PojoWithList();
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(firstName, currentInstance, new StringBuilder("test"));
        Assert.assertTrue(positionalFieldParser.isThisFieldAList());
    }

    @Test
    public void convertAComplexList() throws NoSuchFieldException {
        Field firstName = PojoWithList.class.getDeclaredField("listOfFlatPojos");
        PojoWithList currentInstance = new PojoWithList();
        new PositionalFieldParser(firstName, currentInstance,
                new StringBuilder("ATTRIBUTE1ATTRIBUTE2ATTRIBUTE3ATTRIBUTE4ATTRIBUTE5ATTRIBUTE6")).fillFieldValue();
        Assert.assertEquals(3, currentInstance.getListOfFlatPojos().size());
        FlatPojo lastItemOfTheList = currentInstance.getListOfFlatPojos().get(2);
        String expectedValueForTheAttributeLastName = "ATTRIBUTE6";
        Assert.assertEquals(expectedValueForTheAttributeLastName, lastItemOfTheList.getLastName());
    }

    @Test
    public void generatePositionalString() throws NoSuchFieldException {
        Field firstName = FlatPojo.class.getDeclaredField("firstName");
        FlatPojo currentInstance = new FlatPojo();
        currentInstance.setFirstName("MARCIO GUR");
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(firstName, currentInstance, new StringBuilder());
        Assert.assertEquals("MARCIO GUR", positionalFieldParser.generatePositional());
    }


    @Test
    public void generatePositionalLong() throws NoSuchFieldException {
        Field longAttribute = AccountPojoWithAllSupportedPrimitives.class.getDeclaredField("id");
        AccountPojoWithAllSupportedPrimitives currentInstance = new AccountPojoWithAllSupportedPrimitives();
        Long expectedValue = 99999L;
        currentInstance.setId(expectedValue);
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(longAttribute, currentInstance, new StringBuilder());
        Assert.assertEquals(String.valueOf(expectedValue), positionalFieldParser.generatePositional());
    }

    @Test
    public void generatePositionalInteger() throws NoSuchFieldException {
        Field longAttribute = AccountPojoWithAllSupportedPrimitives.class.getDeclaredField("age");
        AccountPojoWithAllSupportedPrimitives currentInstance = new AccountPojoWithAllSupportedPrimitives();
        Integer expectedValue = 33;
        currentInstance.setAge(expectedValue);
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(longAttribute, currentInstance, new StringBuilder());
        Assert.assertEquals(String.valueOf(expectedValue), positionalFieldParser.generatePositional());
    }

    @Test
    public void generatePositionalBigDecimal() throws NoSuchFieldException {
        Field balance = AccountPojoWithAllSupportedPrimitives.class.getDeclaredField("balance");
        AccountPojoWithAllSupportedPrimitives currentInstance = new AccountPojoWithAllSupportedPrimitives();
        currentInstance.setBalance(new BigDecimal("10000.00"));
        PositionalFieldParser positionalFieldParser = new PositionalFieldParser(balance, currentInstance, new StringBuilder());
        Assert.assertEquals("0010000.00", positionalFieldParser.generatePositional());
    }

    // TODO: HANDLE NULL ATTRIBUTES
}