package org.magu.positionalparser.parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.magu.positionalparser.exception.PositionalParserException;
import org.magu.positionalparser.pojo.FlatPojo;

@RunWith(JUnit4.class)
public class PositionalParserTest 
{
    @Test
    public void testSimpleFlatConvertion() throws PositionalParserException
    {
        FlatPojo pojo = new PositionalParser().toPojo("Nicole    Nagel     ", FlatPojo.class);
        Assert.assertNotNull(pojo);
        Assert.assertNotNull(pojo.getFirstName());
        Assert.assertNotNull(pojo.getLastName());
        
    }
    
}
