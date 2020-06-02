package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalData;
import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.converter.ConverterMapping;
import dev.legrug.positionalparser.exception.PositionalParserException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Márcio Gurgel <marcio.rga@gmail.com>
 */
public class PositionalField {

    private static final String SET_PREFIX = "set";
    private static final int FIRST_CHARACTER_END = 1;
    private static final int FISRT_CHARACTER_BEGIN = 0;
    public static final int ITERATOR_START_POSITIONL = 1;

    private Field currentJavaField;
    private StringBuilder positionalValue;
    private PositionalInfo positionalInfo;
    private Object currentInstance;

    public PositionalField(Field currentJavaField, Object currentInstance, StringBuilder positionalValue) {
        this.currentJavaField = currentJavaField;
        this.positionalValue = positionalValue;
        this.currentInstance = currentInstance;
        PositionalData annotation = currentJavaField.getAnnotation(PositionalData.class);
        if(annotation != null)
        {
            this.positionalInfo = PositionalInfo.fromAnnotaion(annotation);
        }
    }

    public Object buildFieldValue() {

        if(isThisFieldMarkedForConvertion()) {
            if (isThisAPrimitiveValue()) {
                return convertValueAndRemoveUsedPositional(currentJavaField.getType());
            }
            else if(isThisFieldAList())  {
                return convertAttributeList();
            }
            else {
                return convertComplexObject(currentJavaField.getType());
            }
        }

        return null;
    }

    private Object convertAttributeList() {
        int listSize = positionalInfo.getLength();
        List<Object> newList = new ArrayList<>(listSize);
        ParameterizedType parameterizedType = (ParameterizedType) currentJavaField.getGenericType();
        Class<?> classOfTheParameterizedType = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        IntStream.rangeClosed(ITERATOR_START_POSITIONL, listSize).forEach(currentIndex -> {

            if(ConverterMapping.byType(currentJavaField.getType()).isPresent())
            {
                convertListOfPrimitiveValues(newList, classOfTheParameterizedType);
            }
            else
            {
                convertListOfComplexObjects(newList, classOfTheParameterizedType);
            }
        });
        return newList;
    }

    private void convertListOfPrimitiveValues(List<Object> newList, Class<?> classOfTheParameterizedType) {
        Object convertedValue = convertValueAndRemoveUsedPositional(classOfTheParameterizedType);
        newList.add(convertedValue);
    }

    private void convertListOfComplexObjects(List<Object> newList, Class<?> complexObject) {
        Object instanceOfTheComplexObjectFromList = convertComplexObject(complexObject);
        newList.add(instanceOfTheComplexObjectFromList);

        fillValue(newList);
    }

    private Object convertComplexObject(Class<?> classOfTheParameterizedType)  {
       try {

           Object instanceOfTheComplexObjectFromList =
                   classOfTheParameterizedType.getDeclaredConstructor().newInstance();

           Stream.of(classOfTheParameterizedType.getDeclaredFields()).forEach(field -> {
               PositionalField complexPositionalFieldInsideAList = new PositionalField(field, instanceOfTheComplexObjectFromList, positionalValue);
               Object valueForComplexObject = complexPositionalFieldInsideAList.buildFieldValue();
               complexPositionalFieldInsideAList.fillValue(valueForComplexObject);
           });
            return instanceOfTheComplexObjectFromList;
       }
       catch(Exception e) {
           // TODO: Fill the list
           throw new PositionalParserException("There was an error while filling the list []", e);
       }

    }

    public void fillFieldValue() {
        Object fieldValue = buildFieldValue();
        fillValue(fieldValue);
    }


    private Object convertValueAndRemoveUsedPositional(Class<?> type) {
        Optional<Converter> converter = ConverterMapping.byType(type);
        if(converter.isPresent())
        {
            Object convertedValue = converter.get().fromPositional(positionalValue.substring(0, positionalInfo.getLength()), positionalInfo);
            positionalValue.delete(0, positionalInfo.getLength());
            return convertedValue;
        }
        return null;
    }

    private void fillValue(Object convertedValue) {
        try {
            Method setterMethod = currentInstance.getClass().getMethod(buildSetterMethodName(currentJavaField), currentJavaField.getType());
            setterMethod.invoke(currentInstance, convertedValue);
            return;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new PositionalParserException("There was an error while trying to fill field {0} of class {1}");
        }
    }

    public boolean isThisFieldMarkedForConvertion() {
        return positionalInfo != null;
    }

    public boolean isThisAPrimitiveValue() {
        return ConverterMapping.byType(currentJavaField.getType()).isPresent();
    }

    protected boolean isThisFieldAList() {
        Class<?> type = currentJavaField.getType();
        return type.equals(List.class);
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
