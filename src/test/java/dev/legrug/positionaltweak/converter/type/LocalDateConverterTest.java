package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class LocalDateConverterTest extends TestCase {

    public void testFromPositional() {
        PositionalFieldVO positionalFieldVO = new PositionalFieldVO(null) {
            @Override
            public String getPattern() {
                return "yyyyMMdd";
            }

        };
        LocalDate localDate = new LocalDateConverter().fromPositional("20221117", positionalFieldVO);
        Assert.assertEquals(localDate, LocalDate.of(2022, 11, 17));

    }

    public void testToPositional() {
        PositionalFieldVO positionalFieldVO = new PositionalFieldVO(null) {
            @Override
            public String getPattern() {
                return "yyyyMMdd";
            }

            @Override
            public int getLength() {
                return 8;
            }
        };
        String dateAsPosicional = new LocalDateConverter().toPositional(LocalDate.of(2022, 11, 17), positionalFieldVO);
        Assert.assertEquals("20221117", dateAsPosicional);

    }

    public void testToPositionalOverSize() throws PositionalTweakException {
        PositionalFieldVO positionalFieldVO = new PositionalFieldVO(null) {
            @Override
            public String getPattern() {
                return "yyyyMMdd";
            }
        };

        Assert.assertThrows(PositionalTweakException.class, () -> new LocalDateConverter().toPositional(LocalDate.of(2022, 11, 17), positionalFieldVO));


    }
}