package dev.legrug.positionaltweak.parser;

import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.pojo.*;
import dev.legrug.positionaltweak.pojo.account.Adress;
import dev.legrug.positionaltweak.pojo.account.Balance;
import dev.legrug.positionaltweak.pojo.account.UserAccount;
import dev.legrug.positionaltweak.pojo.account.UserAccountWithList;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PositionalTweakTest {

    @Test
    public void toPojoFlatPojo() {

        FlatPojo flatPojoExpected = new FlatPojo("MARCIO", "GURGEL");
        FlatPojo flatPojoGenerated = new PositionalTweak().feedPojo("MARCIO    GURGEL    ", FlatPojo.class);

        Assert.assertEquals(flatPojoExpected, flatPojoGenerated);
    }

    @Test
    public void toPojoFlatPojoWithVariousPrimitiveTypes() {

        AccountPojoWithAllSupportedPrimitives accountPojoExpected =
                new AccountPojoWithAllSupportedPrimitives(1010L, "MARCIO GURGEL", 33, new BigDecimal("77.77"));

        AccountPojoWithAllSupportedPrimitives accountPojoGenerated =
                new PositionalTweak().feedPojo("0000001010MARCIO GURGEL       0330000007777",
                        AccountPojoWithAllSupportedPrimitives.class);

        Assert.assertEquals(accountPojoExpected, accountPojoGenerated);
    }

    @Test
    public void pojoWithAListOfPrimitives() {

        List<Long> listOfLongs = Stream.of(1L, 2L).collect(Collectors.toList());
        List<Integer> listOfIntegers = Stream.of(3, 4).collect(Collectors.toList());
        List<String> listOfStrings = Stream.of("AA", "BB").collect(Collectors.toList());
        List<BigDecimal> listOfBigDecimals = Stream.of("1.1", "2.2").map(current -> new BigDecimal(current)).collect(Collectors.toList());
        PojoWithListsOfPrimitives pojoWithListsOfPrimitives = new PojoWithListsOfPrimitives(listOfLongs, listOfStrings, listOfIntegers, listOfBigDecimals);
        PojoWithListsOfPrimitives pojoWithListsOfPrimitivesGenerated = new PositionalTweak().feedPojo("0102AABB03041.12.2", PojoWithListsOfPrimitives.class);

        Assert.assertEquals(pojoWithListsOfPrimitives, pojoWithListsOfPrimitivesGenerated);
    }

    @Test(expected = PositionalTweakException.class)
    public void fieldIsAnListButDontHaveTheCorrectAnnotation() {
        new PositionalTweak().feedPojo("MARCIO    GURGEL    ", PojoWithListAndWrongAnnotation.class);
    }

    @Test(expected = PositionalTweakException.class)
    public void fieldWithNoLenth() {
        new PositionalTweak().feedPojo("MARCIO    GURGEL    ", PojoWithAttributeThatHasNoLength.class);
    }


    @Test
    public void generatePositionalPojoWithPrimitives() {

        AccountPojoWithAllSupportedPrimitives pojoOfPrimitives = new AccountPojoWithAllSupportedPrimitives();
        pojoOfPrimitives.setId(99L);
        pojoOfPrimitives.setClientName("Marcio");
        pojoOfPrimitives.setAge(33);
        pojoOfPrimitives.setBalance(new BigDecimal("77.77"));

        String expectedPositionalString = "0000000099Marcio              0330000077.77";
        String generatedPositional = new PositionalTweak().generatePositional(pojoOfPrimitives);

        Assert.assertEquals(expectedPositionalString, generatedPositional);
    }

    @Test
    public void generatePositionalPojoWithComplexObjects() {

        UserAccount userAccount = new UserAccount();
        userAccount.setBalance(new Balance(new BigDecimal("0.10"), new BigDecimal("100000.00")));
        userAccount.setAdress(new Adress("72900", "Brasilia"));

        String generatedPositional = new PositionalTweak().generatePositional(userAccount);
        Assert.assertEquals("0000000.100100000.0072900     Brasilia                                          ", generatedPositional);
        // Generate the POJO back from the recently generated positional
        UserAccount userAccountFromPositional = new PositionalTweak().feedPojo(generatedPositional, UserAccount.class);
        Assert.assertEquals(userAccount, userAccountFromPositional);
    }

    @Test
    public void generatePositionalPojoWithComplexObjectsAndList() {

        UserAccountWithList userAccount = new UserAccountWithList();
        Balance balance1 = new Balance(new BigDecimal("0.10"), new BigDecimal("100000.00"));
        Balance balance2 = new Balance(new BigDecimal("0.77"), new BigDecimal("700000.00"));
        ArrayList<Balance> balances = new ArrayList<>();
        balances.add(balance1);
        balances.add(balance2);
        userAccount.setBalances(balances);


        Adress brasilia = new Adress("72900", "Brasilia");
        Adress bambui = new Adress("77777", "Bambui");
        ArrayList<Adress> adresses = new ArrayList<>();
        adresses.add(brasilia);
        adresses.add(bambui);
        userAccount.setAdresses(adresses);

        String generatedPositional = new PositionalTweak().generatePositional(userAccount);
        System.out.println(generatedPositional);
    }

    @Test
    public void testDateConvertionToPositional() {

        PojoWithADate pojoWithADate = new PojoWithADate();
        String generatedPositional = new PositionalTweak().generatePositional(pojoWithADate);
        System.out.println(generatedPositional);
        Assert.assertNotNull(generatedPositional);
    }

    @Test
    public void testDateConvertionToObject() {

        PojoWithADate pojoWithADate = new PositionalTweak().feedPojo("20211118",PojoWithADate.class);
        System.out.println(pojoWithADate.getSomeRandomDate());
        Assert.assertNotNull(pojoWithADate);
    }
    
}