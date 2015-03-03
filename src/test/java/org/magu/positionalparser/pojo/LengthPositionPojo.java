package org.magu.positionalparser.pojo;

import org.magu.positionalparser.annotation.PositionalData;

/**
 * Length position example
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class LengthPositionPojo 
{

    @PositionalData(position = 1, length = 2)
    private Integer lengthOfNexField;
    
    @PositionalData(position = 2, lengthPosition = 1)
    private String fieldWithDynamicLength;

    public Integer getLengthOfNexField() {
        return lengthOfNexField;
    }

    public void setLengthOfNexField(Integer lengthOfNexField) {
        this.lengthOfNexField = lengthOfNexField;
    }

    public String getFieldWithDynamicLength() {
        return fieldWithDynamicLength;
    }

    public void setFieldWithDynamicLength(String fieldWithDynamicLength) {
        this.fieldWithDynamicLength = fieldWithDynamicLength;
    }
    
}
