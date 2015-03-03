package org.magu.positionalparser.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

import org.magu.positionalparser.annotation.PositionalData;
import org.magu.positionalparser.converter.Converter;
import org.magu.positionalparser.converter.ConverterMapping;
import org.magu.positionalparser.exception.PositionalParserException;
import org.magu.positionalparser.vo.ConversionDetails;

/**
 * APIs front end.
 * 
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PositionalParser {

    
    private static final String SET_PREFIX = "set";
    private static final int FIRST_CHARACTER_END = 1;
    private static final int FISRT_CHARACTER_BEGIN = 0;
    private Integer lastPositionReaded = 0;
    
    /** Register field already read and its identifier (position) **/
    private Map<Integer, ConversionDetails> readAnnotations = new TreeMap<>();

    /**
     * API's <i>entry-point</i>: handles user's request.
     * 
     * @param positionalString 
     *          Raw <i>positional</i> message, to be converted
     *          
     * @param pojoClass 
     *          POJO's class, which will be filled by the conversion process.
     *          <b>Important: </b> the POJO's attributes <b>MUST</b> be annotated with {@link PositionalData}
     *          
     * @return a instance of <i>pojoClass</i>
     * @throws PositionalParserException
     */
    public <T> T toPojo(String positionalString, Class<T> pojoClass) throws PositionalParserException {

        try 
        {
            T pojoInstance = pojoClass.newInstance();
            Field[] declaredFields = pojoClass.getDeclaredFields();
            
            for(Field field : declaredFields)
            {
                PositionalData positionalData = field.getAnnotation(PositionalData.class);
                
                if(positionalData != null)
                {
                    // TODO 1: assert that lengthPosition and length are not filled at same time
                    // TODO 2: if lengthPosition was specified, use it instead of "positionalData"
                    // TODO 3: Make recursive calls in case multivalued lists
                    int fieldPosition = positionalData.position();
                    
                    /** Assert that a posistion wasnt read before **/
                    if(!readAnnotations.containsKey(fieldPosition))
                    {
                        /** Length may be dynamic **/
                        int fieldLength = identifyLength(positionalData);

                        /** Find out the range of the current field and increment last readed position **/
                        String fieldData = positionalString.substring(lastPositionReaded, lastPositionReaded += fieldLength);
                        
                        Class<?> currentFieldClassType = field.getType();
                        Converter<?> converterAssociatedToField = ConverterMapping.findConverterFor(currentFieldClassType);
                        
                        /** Invokes a specifc converter for the field's type **/
                        Object convertedValue = converterAssociatedToField.convert(fieldData, positionalData);
                        
                        Method setterMethod = pojoClass.getMethod(buildSetterMethodName(field), currentFieldClassType);
                        setterMethod.invoke(pojoInstance, convertedValue);
                        
                        /** Register converstion details, it may be useful **/
                        readAnnotations.put(fieldPosition, new ConversionDetails(positionalData, convertedValue));
                    }
                    else
                    {
                        throw new PositionalParserException(
                                "The attribute \"position\" was filled more than once with the value {0}", fieldPosition);
                    }
                    
                }
                // TODO: Should non-mapped attributes be logged?
            }
            
            return pojoInstance;
        } 
        catch (InstantiationException | IllegalAccessException 
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
        {
            throw new PositionalParserException("There was an excepton handling the request. See original the exception.", e);
        } 
        
    }

    /**
     * Identify which position to use: {@link PositionalData#length()} or {@link PositionalData#lengthPosition()}.
     * The  {@link PositionalData#lengthPosition()} takes precedence in relation to {@link PositionalData#length()}
     * @param positionalData Current annotation (cor current parsing field)
     * @return Length in which belongs to the current field
     * @throws PositionalParserException 
     */
    private int identifyLength(PositionalData positionalData) throws PositionalParserException 
    {
        int lengthPosition = positionalData.lengthPosition();
        
        if(lengthPosition != 0)
        {
            ConversionDetails conversionDetails = readAnnotations.get(lengthPosition);
            if(conversionDetails.getConvertedValue() != null)
            {
                // TODO: Verify if "convertedValue" is a number (integer) otherwise throw exception
                return Integer.valueOf(conversionDetails.getConvertedValue().toString());
            }
            else
            {
                /** The reference to length position is valid  **/
                throw new PositionalParserException(MessageFormat.format(
                        "Invalid \"length position\" specified for position {0}", positionalData.position()));
            }
        }
        
        /** If there's no length position, the "normal" position is passed **/
        return positionalData.length();
    }

    /**
     * Build's a <i>setter's</i> method name, based on the field's name.
     * @param field Java field (base to get its name)
     * @return Setter for current field
     */
    private String buildSetterMethodName(Field field) 
    {
        String fieldName = field.getName();
        StringBuilder stringBuilder = new StringBuilder()
            .append(SET_PREFIX).append(Character.toUpperCase(fieldName.charAt(FISRT_CHARACTER_BEGIN)))
            .append(fieldName.substring(FIRST_CHARACTER_END));
        return stringBuilder.toString();
    }

}
