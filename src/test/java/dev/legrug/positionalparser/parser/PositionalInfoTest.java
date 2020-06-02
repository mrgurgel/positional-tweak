package dev.legrug.positionalparser.parser;

import org.junit.Assert;
import org.junit.Test;

public class PositionalInfoTest {

    @Test
    public void createFromAnnotation() {
        ClassThatImplementsPositionalAnnotation positionalData = new ClassThatImplementsPositionalAnnotation();
        PositionalInfo positionalInfo =
                PositionalInfo.fromAnnotaion(positionalData);

        Assert.assertEquals(positionalInfo.getLength(), positionalData.length());
        Assert.assertEquals(positionalInfo.getPattern(), positionalData.pattern());
        Assert.assertEquals(positionalInfo.getPrecision(), positionalData.precision());
        Assert.assertEquals(positionalInfo.isTrim(), positionalData.trim());

    }

}