package org.magu.positionalparser.parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.magu.positionalparser.annotation.PositionalData;
import org.magu.positionalparser.exception.PositionalParserException;
import org.magu.positionalparser.pojo.FlatPojo;
import org.magu.positionalparser.pojo.LengthPositionPojo;

/**
 * Tests the main functionality with variation of parameters
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
@RunWith(JUnit4.class)
public class PositionalParserTest 
{
    /**
     * Tests a simple string convertion
     * @throws PositionalParserException
     */
    @Test
    public void testSimpleFlatConvertion() throws PositionalParserException
    {
        FlatPojo pojo = new PositionalParser().toPojo("Nicole    Nagel     ", FlatPojo.class);
        Assert.assertNotNull(pojo);
        Assert.assertNotNull(pojo.getFirstName());
        Assert.assertNotNull(pojo.getLastName());
        
    }
    
    /**
     * Tests the usage of {@link PositionalData#lengthPosition()}
     * @throws PositionalParserException
     */
    @Test
    public void testStringWithLengthPosition() throws PositionalParserException
    {
        LengthPositionPojo pojo = new PositionalParser().toPojo("10ABCDEFGHIJ", LengthPositionPojo.class);
        Assert.assertNotNull(pojo);
        Assert.assertEquals(10, pojo.getFieldWithDynamicLength().length());

    }
    
}
