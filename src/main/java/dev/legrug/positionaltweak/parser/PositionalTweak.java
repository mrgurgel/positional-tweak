package dev.legrug.positionaltweak.parser;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.exception.PositionalTweakException;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * APIs front end.
 * 
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PositionalTweak {


    /**
     * API's <i>entry-point</i>: handles user's request.
     *
     * @param positionalString Raw <i>positional</i> message, to be converted
     * @param pojoClass        POJO's class, which will be filled by the conversion process.
     *                         <b>Important: </b> the POJO's attributes <b>MUST</b> be annotated with {@link PositionalField}
     * @return a instance of <i>pojoClass</i>
     * @throws PositionalTweakException
     */
    public <T> T feedPojo(String positionalString, Class<T> pojoClass) {

        try {
            T pojoInstance = pojoClass.getDeclaredConstructor().newInstance();
            StringBuilder positionalStringValue = new StringBuilder(positionalString);

            Stream.of(pojoClass.getDeclaredFields()).forEach(field -> {
                PositionalFieldParser positionalFieldParser = new PositionalFieldParser(field, pojoInstance, positionalStringValue);
                positionalFieldParser.fillFieldValue();

            });

            return pojoInstance;
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new PositionalTweakException("There was an excepton handling the request. See original the exception.", e);
        }

    }

    public String generatePositional(Object pojoInstance) {

        try {
            StringBuilder positionalStringValue = new StringBuilder();

            Stream.of(pojoInstance.getClass().getDeclaredFields()).forEach(field -> {
                PositionalFieldParser positionalFieldParser = new PositionalFieldParser(field, pojoInstance, positionalStringValue);
                positionalFieldParser.generatePositional();
            });
            return positionalStringValue.toString();
        }
        catch (IllegalArgumentException | SecurityException e) {
            throw new PositionalTweakException("There was an excepton handling the request. See original the exception.", e);
        }

    }

}
