package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.exception.PositionalParserException;
import dev.legrug.positionalparser.pojo.AccountPojoWithAllSupportedPrimitives;
import dev.legrug.positionalparser.pojo.FlatPojo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PositionalParserTest {

    @Test
    public void toPojoFlatPojo() {

        FlatPojo flatPojoExpected = new FlatPojo("MARCIO", "GURGEL");
        FlatPojo flatPojoGenerated = new PositionalParser().toPojo("MARCIO    GURGEL    ", FlatPojo.class);

        Assert.assertEquals(flatPojoExpected, flatPojoGenerated);
    }

    @Test
    public void toPojoFlatPojoWithVariousPrimitiveTypes() {

        AccountPojoWithAllSupportedPrimitives accountPojoExpected = new AccountPojoWithAllSupportedPrimitives(1010L, "Márcio Gurgel", 33, new BigDecimal("77.77"));
        AccountPojoWithAllSupportedPrimitives accountPojoGenerated = new PositionalParser().toPojo("0000001010MARCIO GURGEL       0330000007777", AccountPojoWithAllSupportedPrimitives.class);

        Assert.assertEquals(accountPojoExpected, accountPojoGenerated);
    }

    @Test
    public void pojoWithAListOfPrimitives() {
    }


    @Test
    public void pojoWithAListOfComplex() {
    }
}