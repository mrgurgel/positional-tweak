package org.magu.positionalparser.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.magu.positionalparser.annotation.PositionalData;
import org.magu.positionalparser.converter.Converter;
import org.magu.positionalparser.converter.ConverterPool;
import org.magu.positionalparser.exception.PositionalParserException;

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
    private Map<Integer, PositionalData> positionMapping = new TreeMap<>();

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
                    
                    if(!positionMapping.containsKey(fieldPosition))
                    {
                        /** Find out the range of the current field and increment last readed position **/
                        String fieldData = positionalString.substring(lastPositionReaded, lastPositionReaded += positionalData.length());
                        
                        Class<?> fieldType = field.getType();
                        Converter<?> findConverterFor = ConverterPool.findConverterFor(fieldType);
                        
                        /** Invokes a specifc converter for the field's type **/
                        Object convertedValue = findConverterFor.convert(fieldData, positionalData);
                        
                        Method setterMethod = pojoClass.getMethod(buildSetterMethodName(field), fieldType);
                        setterMethod.invoke(pojoInstance, convertedValue);
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
     * Build's a <i>setter's</i> method name, based on the field's name.
     * @param field 
     * @return Method's name
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
