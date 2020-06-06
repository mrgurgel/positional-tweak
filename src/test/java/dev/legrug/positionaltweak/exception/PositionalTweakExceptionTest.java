package dev.legrug.positionaltweak.exception;

import org.junit.Assert;
import org.junit.Test;

public class PositionalTweakExceptionTest {

    @Test
    public void testMessageString() {

        PositionalTweakException positionalException =
                new PositionalTweakException("There was an error when: {0}", "I've tried to do something");

        Assert.assertEquals("There was an error when: I've tried to do something", positionalException.getMessage());
    }

}