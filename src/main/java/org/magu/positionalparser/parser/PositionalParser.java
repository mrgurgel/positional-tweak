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
     * 
     * @param positional
     * @param pojoClass
     * @return
     * @throws PositionalParserException
     */
    public <T> T toPojo(String positional, Class<T> pojoClass) throws PositionalParserException {

        try 
        {
            T pojoInstance = pojoClass.newInstance();
            Field[] declaredFields = pojoClass.getDeclaredFields();
            
            for(Field field : declaredFields)
            {
                PositionalData positionalData = field.getAnnotation(PositionalData.class);
                
                if(positionalData != null)
                {
                    int fieldPosition = positionalData.position();
                    
                    if(!positionMapping.containsKey(fieldPosition))
                    {
                        int fieldLength = positionalData.length();
                        String fieldData = positional.substring(lastPositionReaded, lastPositionReaded += fieldLength);
                        
                        Class<?> fieldType = field.getType();
                        Converter<?> findConverterFor = ConverterPool.findConverterFor(fieldType);
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
        catch (InstantiationException | IllegalAccessException e) 
        {
            throw new PositionalParserException("It was not possible to instantiate bean", e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;

    }

    private String buildSetterMethodName(Field field) {
        String fieldName = field.getName();
        StringBuilder stringBuilder = new StringBuilder()
            .append(SET_PREFIX).append(Character.toUpperCase(fieldName.charAt(FISRT_CHARACTER_BEGIN)))
            .append(fieldName.substring(FIRST_CHARACTER_END));
        return stringBuilder.toString();
    }

}
