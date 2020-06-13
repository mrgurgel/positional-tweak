package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalList;
import dev.legrug.positionaltweak.annotation.PositionalMonetary;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;
import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

public class BigDecimalConverterTest extends TestCase {

    public void testFromPositionalWithDot() {
        PositionalFieldVO positionalFieldVO = new PositionalFieldVO(buildPositionalAnnotationForBigDecimalWithDecimalSepartor());
        String expectedValue = "10.10";
        BigDecimal pojoFieldValue = new BigDecimal(expectedValue);
        String generatedValue = new BigDecimalConverter().toPositional(pojoFieldValue, positionalFieldVO);
        Assert.assertEquals(expectedValue, generatedValue);
    }

    public void testFromPositionalWithoutDot() {
        PositionalFieldVO positionalFieldVO = new PositionalFieldVO(buildPositionalAnnotationForBigDecimalWithoutDecimalSepartor());
        String expectedValue = "01010";
        BigDecimal pojoFieldValue = new BigDecimal("10.10");
        String generatedValue = new BigDecimalConverter().toPositional(pojoFieldValue, positionalFieldVO);
        Assert.assertEquals(expectedValue, generatedValue);
    }

    public void testToPositionalWithDot() {
        PositionalFieldVO positionalFieldVO = new PositionalFieldVO(buildPositionalAnnotationForBigDecimalWithDecimalSepartor());
        BigDecimal generetedValue = new BigDecimalConverter().fromPositional("10.10", positionalFieldVO);
        Assert.assertEquals(new BigDecimal("10.10"), generetedValue);
    }


    private PositionalField buildPositionalAnnotationForBigDecimalWithDecimalSepartor() {
        return new PositionalField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public int length() {
                return 5;
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
            public PositionalList listInfo() {
                return null;
            }

            @Override
            public PositionalMonetary monetaryInfo() {
                return new PositionalMonetary(){
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return null;
                    }

                    @Override
                    public int numberOfDecimalPlaces() {
                        return 2;
                    }

                    @Override
                    public String monetaryPattern() {
                        return null;
                    }

                    @Override
                    public String decimalSeparator() {
                        return ".";
                    }

                    {

                    }};
            }

            {

            }};
    }
    private PositionalField buildPositionalAnnotationForBigDecimalWithoutDecimalSepartor() {
        return new PositionalField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public int length() {
                return 5;
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
            public PositionalList listInfo() {
                return null;
            }

            @Override
            public PositionalMonetary monetaryInfo() {
                return new PositionalMonetary(){
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return null;
                    }

                    @Override
                    public int numberOfDecimalPlaces() {
                        return 2;
                    }

                    @Override
                    public String monetaryPattern() {
                        return null;
                    }

                    @Override
                    public String decimalSeparator() {
                        return "";
                    }

                    {

                    }};
            }

            {

            }};
    }

}