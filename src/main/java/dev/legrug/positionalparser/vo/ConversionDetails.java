package dev.legrug.positionalparser.vo;

import dev.legrug.positionalparser.annotation.PositionalData;

/**
 * Register relevant conversion data
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class ConversionDetails 
{
    /** Original annotation **/
    private PositionalData positionalData;
    
    /** Converted data **/
    private Object convertedValue;
    
    
    /**
     * Build a new instance with relevant information
     * @param positionalData Annotation with descriptors
     * @param convertedValue Positional converted into object
     */
    public ConversionDetails(PositionalData positionalData, Object convertedValue) {
        super();
        this.positionalData = positionalData;
        this.convertedValue = convertedValue;
    }
    
    public PositionalData getPositionalData() {
        return positionalData;
    }
    
    public Object getConvertedValue() {
        return convertedValue;
    }
    
}
