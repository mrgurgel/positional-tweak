package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.parser.vo.PositionalFieldVO;
import org.junit.Assert;
import org.junit.Test;

public class PositionalFieldVOTest {

    @Test
    public void createFromAnnotation() {
        ClassThatImplementsPositionalAnnotation positionalData = new ClassThatImplementsPositionalAnnotation();
        PositionalFieldVO positionalFieldVO =
                PositionalFieldVO.fromAnnotaion(positionalData);

        Assert.assertEquals(positionalFieldVO.getLength(), positionalData.length());
        Assert.assertEquals(positionalFieldVO.getPattern(), positionalData.pattern());
        Assert.assertEquals(positionalFieldVO.isTrim(), positionalData.trim());

    }

}