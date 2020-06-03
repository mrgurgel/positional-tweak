package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.exception.PositionalParserException;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * APIs front end.
 * 
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PositionalParser {


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
            StringBuilder positionalValue = new StringBuilder(positionalString);

            Stream.of(pojoClass.getDeclaredFields()).forEach(field -> {
                PositionalField positionalField = new PositionalField(field, pojoInstance, positionalValue);
                positionalField.fillFieldValue();

            });

            return pojoInstance;
        }
        catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
        {
            throw new PositionalParserException("There was an excepton handling the request. See original the exception.", e);
        }
        
    }


}
