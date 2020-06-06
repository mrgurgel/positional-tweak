package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.pojo.AccountPojoWithAllSupportedPrimitives;
import dev.legrug.positionalparser.pojo.FlatPojo;
import dev.legrug.positionalparser.pojo.PojoWithListsOfPrimitives;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PositionalParserTest {

    @Test
    public void toPojoFlatPojo() {

        FlatPojo flatPojoExpected = new FlatPojo("MARCIO", "GURGEL");
        FlatPojo flatPojoGenerated = new PositionalParser().toPojo("MARCIO    GURGEL    ", FlatPojo.class);

        Assert.assertEquals(flatPojoExpected, flatPojoGenerated);
    }

    @Test
    public void toPojoFlatPojoWithVariousPrimitiveTypes() {

        AccountPojoWithAllSupportedPrimitives accountPojoExpected =
                new AccountPojoWithAllSupportedPrimitives(1010L, "MARCIO GURGEL", 33, new BigDecimal("77.77"));

        AccountPojoWithAllSupportedPrimitives accountPojoGenerated =
                new PositionalParser().toPojo("0000001010MARCIO GURGEL       0330000007777",
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
        PojoWithListsOfPrimitives pojoWithListsOfPrimitivesGenerated = new PositionalParser().toPojo("0102AABB03041.12.2", PojoWithListsOfPrimitives.class);

        Assert.assertEquals(pojoWithListsOfPrimitives, pojoWithListsOfPrimitivesGenerated);
    }


}