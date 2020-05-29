package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.converter.ConverterMapping;
import dev.legrug.positionalparser.exception.PositionalParserException;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @author Márcio Gurgel <marcio.rga@gmail.com>
 */
public class PositionalField {

    private Field currentJavaField;
    private String positionalValue;
    private PositionalData positionalData;

    public PositionalField(Field currentJavaField, String positionalValue) {
        this.currentJavaField = currentJavaField;
        this.positionalValue = positionalValue;
        this.positionalData = currentJavaField.getAnnotation(PositionalData.class);
    }

    public Object extractPositionalValue() {

        Class<?> type = currentJavaField.getType();
        Optional<Converter> converter = ConverterMapping.byType(type);
        if(converter.isPresent()){
            return converter.get()
                    .convert(positionalValue.substring(0, positionalData.length()), positionalData);
        }
        throw new PositionalParserException("There's no conversor associated to the field: {0}", type.getName());
    }
}
