package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.parser.PositionalTweak;
import dev.legrug.positionaltweak.pojo.BigDecimalExample;
import dev.legrug.positionaltweak.pojo.BigDecimalExampleWithDot;
import dev.legrug.positionaltweak.pojo.BigDecimalExampleWithLetter;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalMonetaryTest {

    @Test
    public void testToPositional() {
        BigDecimalExample pojoInstance = new BigDecimalExample();
        BigDecimal money = new BigDecimal("400000");
        pojoInstance.setMoney(money);
        String generatedString = new PositionalTweak().generatePositional(pojoInstance);

        Assert.assertEquals("000000040000000", generatedString);

        BigDecimalExample bigDecimalExample = new PositionalTweak().feedPojo(generatedString, BigDecimalExample.class);
        Assert.assertTrue(money.compareTo(bigDecimalExample.getMoney()) == 0);
    }

    @Test
    public void testToPositional2() {
        BigDecimalExample pojoInstance = new BigDecimalExample();
        BigDecimal money = new BigDecimal("400000.12");
        pojoInstance.setMoney(money);
        String generatedString = new PositionalTweak().generatePositional(pojoInstance);

        Assert.assertEquals("000000040000012", generatedString);

        BigDecimalExample bigDecimalExample = new PositionalTweak().feedPojo(generatedString, BigDecimalExample.class);
        Assert.assertTrue(money.compareTo(bigDecimalExample.getMoney()) == 0);

    }


    @Test
    public void testToPositional3() {
        BigDecimalExampleWithDot pojoInstance = new BigDecimalExampleWithDot();
        BigDecimal money = new BigDecimal("400000.12");
        pojoInstance.setMoney(money);
        String generatedString = new PositionalTweak().generatePositional(pojoInstance);

        Assert.assertEquals("000000400000.12", generatedString);

        BigDecimalExampleWithDot bigDecimalExample = new PositionalTweak().feedPojo(generatedString, BigDecimalExampleWithDot.class);
        Assert.assertTrue(money.compareTo(bigDecimalExample.getMoney()) == 0);

    }

    @Test
    public void testToPositional4() {
        BigDecimalExampleWithLetter pojoInstance = new BigDecimalExampleWithLetter();
        BigDecimal money = new BigDecimal("400000.120");
        pojoInstance.setMoney(money);
        String generatedString = new PositionalTweak().generatePositional(pojoInstance);

        Assert.assertEquals("000000400000B120", generatedString);

        BigDecimalExampleWithLetter bigDecimalExample = new PositionalTweak().feedPojo(generatedString, BigDecimalExampleWithLetter.class);
        Assert.assertTrue(money.compareTo(bigDecimalExample.getMoney()) == 0);

    }


    @Test
    public void testToPositional5() {
        BigDecimalExample pojoInstance = new BigDecimalExample();
        BigDecimal money = new BigDecimal("0.12");
        pojoInstance.setMoney(money);
        String generatedString = new PositionalTweak().generatePositional(pojoInstance);

        Assert.assertEquals("000000000000012", generatedString);

        BigDecimalExample bigDecimalExample = new PositionalTweak().feedPojo(generatedString, BigDecimalExample.class);
        Assert.assertTrue(money.compareTo(bigDecimalExample.getMoney()) == 0);

    }

    @Test
    public void testToPositional6() {
        BigDecimalExample pojoInstance = new BigDecimalExample();
        BigDecimal money = new BigDecimal("0.01");
        pojoInstance.setMoney(money);
        String generatedString = new PositionalTweak().generatePositional(pojoInstance);

        Assert.assertEquals("000000000000001", generatedString);

        BigDecimalExample bigDecimalExample = new PositionalTweak().feedPojo(generatedString, BigDecimalExample.class);
        Assert.assertTrue(money.compareTo(bigDecimalExample.getMoney()) == 0);

    }
}
