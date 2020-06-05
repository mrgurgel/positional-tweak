package dev.legrug.positionalparser.parser;

import dev.legrug.positionalparser.annotation.PositionalEvict;
import dev.legrug.positionalparser.annotation.PositionalField;
import dev.legrug.positionalparser.annotation.PositionalList;
import dev.legrug.positionalparser.annotation.PositionalMonetary;
import dev.legrug.positionalparser.converter.Converter;
import dev.legrug.positionalparser.converter.ConverterMapping;
import dev.legrug.positionalparser.exception.PositionalParserException;
import dev.legrug.positionalparser.parser.vo.PositionalDataVO;
import dev.legrug.positionalparser.parser.vo.PositionalFieldVO;
import dev.legrug.positionalparser.parser.vo.PositionalListVO;
import dev.legrug.positionalparser.parser.vo.PositionalMonetaryVO;

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
public class PositionalFieldParser {

    private static final String SET_PREFIX = "set";
    private static final int FIRST_CHARACTER_END = 1;
    private static final int FISRT_CHARACTER_BEGIN = 0;
    public static final int ITERATOR_START_POSITIONL = 1;

    private Field currentJavaField;
    private StringBuilder positionalValue;
    private Object currentInstance;
    private PositionalDataVO positionalDataVO;

    public PositionalFieldParser(Field currentJavaField, Object currentInstance, StringBuilder positionalValue) {
        this.currentJavaField = currentJavaField;
        this.positionalValue = positionalValue;
        this.currentInstance = currentInstance;
        this.positionalDataVO = new PositionalDataVO();
        PositionalField positionalField = currentJavaField.getAnnotation(PositionalField.class);
        if(positionalField != null)
        {
            this.positionalDataVO.setPositionalFieldVO(PositionalFieldVO.fromAnnotaion(positionalField));
        }

        extractListInfoIfAny();
        extractMonetaryInfoIfAny();
    }

    private void extractListInfoIfAny() {
        PositionalList positionalList = currentJavaField.getAnnotation(PositionalList.class);
        if(positionalList != null)
        {
            this.positionalDataVO.setPositionalListVO(PositionalListVO.fromAnnotaion(positionalList));
        }
    }

    private void extractMonetaryInfoIfAny() {
        PositionalMonetary positionalMonetary = currentJavaField.getAnnotation(PositionalMonetary.class);
        if(positionalMonetary != null)
        {
            this.positionalDataVO.setPositionalMonetaryVO(PositionalMonetaryVO.fromAnnotaion(positionalMonetary));
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
        int listOccurences = positionalDataVO.getPositionalListVO().getOccurrences();
        List<Object> newList = new ArrayList<>(listOccurences);
        ParameterizedType parameterizedType = (ParameterizedType) currentJavaField.getGenericType();
        Class<?> classOfTheParameterizedType = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        IntStream.rangeClosed(ITERATOR_START_POSITIONL, listOccurences).forEach(currentIndex -> {

            if(ConverterMapping.byType(classOfTheParameterizedType).isPresent())
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
               PositionalFieldParser complexPositionalFieldParserInsideAList = new PositionalFieldParser(field, instanceOfTheComplexObjectFromList, positionalValue);
               Object valueForComplexObject = complexPositionalFieldParserInsideAList.buildFieldValue();
               complexPositionalFieldParserInsideAList.fillValue(valueForComplexObject);
           });
            return instanceOfTheComplexObjectFromList;
       }
       catch(Exception e) {
           throw new PositionalParserException("There was an error while filling the class {0}", e, classOfTheParameterizedType);
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
            Object convertedValue = converter.get().fromPositional(positionalValue.substring(0, positionalDataVO.getPositionalFieldVO().getLength()), positionalDataVO);
            positionalValue.delete(0, positionalDataVO.getPositionalFieldVO().getLength());
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
        return currentJavaField.getAnnotation(PositionalEvict.class) == null;
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
