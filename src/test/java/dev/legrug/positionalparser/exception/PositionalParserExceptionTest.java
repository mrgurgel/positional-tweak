package dev.legrug.positionalparser.exception;

import org.junit.Assert;
import org.junit.Test;

public class PositionalParserExceptionTest {

    @Test
    public void testMessageString() {

        PositionalParserException positionalException =
                new PositionalParserException("There was an error when: {0}", "I've tried to do something");

        Assert.assertEquals("There was an error when: I've tried to do something", positionalException.getMessage());
    }

}