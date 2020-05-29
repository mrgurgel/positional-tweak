package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.exception.PositionalParserException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

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
            T pojoInstance = pojoClass.getDeclaredConstructor().newInstance();

            Stream.of(pojoClass.getDeclaredFields()).forEach(field -> {

                PositionalData positionalData = field.getDeclaredAnnotation(PositionalData.class);


            });

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
