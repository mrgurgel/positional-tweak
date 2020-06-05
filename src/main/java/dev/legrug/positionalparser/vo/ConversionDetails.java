package dev.legrug.positionalparser.vo;

import dev.legrug.positionalparser.annotation.PositionalField;

/**
 * Register relevant conversion data
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class ConversionDetails 
{
    /** Original annotation **/
    private PositionalField positionalField;
    
    /** Converted data **/
    private Object convertedValue;
    
    
    /**
     * Build a new instance with relevant information
     * @param positionalField Annotation with descriptors
     * @param convertedValue Positional converted into object
     */
    public ConversionDetails(PositionalField positionalField, Object convertedValue) {
        super();
        this.positionalField = positionalField;
        this.convertedValue = convertedValue;
    }
    
    public PositionalField getPositionalField() {
        return positionalField;
    }
    
    public Object getConvertedValue() {
        return convertedValue;
    }
    
}
