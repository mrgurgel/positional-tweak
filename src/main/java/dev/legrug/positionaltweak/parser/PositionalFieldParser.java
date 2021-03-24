package dev.legrug.positionaltweak.parser;

import dev.legrug.positionaltweak.annotation.PositionalEvict;
import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.converter.ConverterMapping;
import dev.legrug.positionaltweak.exception.PositionalTweakException;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

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
    private static final String GET_PREFIX = "get";
    private static final int FIRST_CHARACTER_END = 1;
    private static final int FISRT_CHARACTER_BEGIN = 0;
    public static final int ITERATOR_START_POSITIONAL = 1;
    public static final int NOT_INFORMED = -1;

    private Field currentJavaField;
    private StringBuilder positionalValue;
    private Object currentInstance;
    private PositionalFieldVO positionalFieldVO;

    public PositionalFieldParser(Field currentJavaField, Object currentInstance, StringBuilder positionalValue) {
        this.currentJavaField = currentJavaField;
        this.positionalValue = positionalValue;
        this.currentInstance = currentInstance;
        this.positionalFieldVO = new PositionalFieldVO(currentJavaField.getAnnotation(PositionalField.class));
    }


    private Object buildFieldValue() {

        if (isThisFieldMarkedForConvertion()) {
            if (isThisAPrimitiveValue()) {
                return convertValueAndRemoveUsedPositional(currentJavaField.getType());
            } else if (isThisFieldAList()) {
                return convertAttributeList();
            } else {
                return convertComplexObject(currentJavaField.getType());
            }
        }

        return null;
    }

    public String generatePositional() {

        if (isThisAPrimitiveValue()) {
            return generatePositionalForPrimitive();
        } else if (isThisFieldAList()) {
            generatePositionalForList();
        }
        else {
            generatePositionalForComplexObject();
        }
        return null;
    }

    private void generatePositionalForComplexObject() {
        try {
            Method getterMethod = currentInstance.getClass().getMethod(buildMethodName(currentJavaField, GET_PREFIX));
            Object complexObjectInstance = getterMethod.invoke(currentInstance);

            Stream.of(complexObjectInstance.getClass().getDeclaredFields()).forEach(complexObjectField -> {
                PositionalFieldParser positionalFieldParser = new PositionalFieldParser(complexObjectField, complexObjectInstance, positionalValue);
                positionalFieldParser.generatePositional();
            });

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void generatePositionalForList() {
        try {
            Method getterMethod = currentInstance.getClass().getMethod(buildMethodName(currentJavaField, GET_PREFIX));
            List complexObjectInstance = (List) getterMethod.invoke(currentInstance);

            validateMaxLimitReached(complexObjectInstance);
            complexObjectInstance.forEach(iterationObject -> {

                Stream.of(iterationObject.getClass().getDeclaredFields()).forEach(field -> {
                    PositionalFieldParser positionalFieldParser = new PositionalFieldParser(field, iterationObject, positionalValue);
                    positionalFieldParser.generatePositional();
                });

            });

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private String generatePositionalForPrimitive() {
        try {
            Converter<? super Object> converter = ConverterMapping.byType(currentJavaField.getType()).get();
            Method getterMethod = currentInstance.getClass().getMethod(buildMethodName(currentJavaField, GET_PREFIX));
            Object pojoFieldValue = getterMethod.invoke(currentInstance);

            String generatedPositional = converter.toPositional(pojoFieldValue, positionalFieldVO);
            positionalValue.append(generatedPositional);
            return generatedPositional;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PositionalTweakException("A error has ocurred when generating positional to the field: {0}. " +
                    "Verify if the field has the get method", e,
                    currentJavaField.getName());
        }
    }

    private void validateMaxLimitReached(List complexObjectInstance) {
        int occurrences = positionalFieldVO.getPositionalListVO().getOccurrences();
        if(complexObjectInstance.size() > occurrences) {
            throw new PositionalTweakException("The list field {} has more elements" +
                    " than registered in the annotation. Actual: {}, registered: {}",
                    currentJavaField, complexObjectInstance.size(), occurrences);
        }
    }

    private Object convertAttributeList() {
        stopProcessingIfTheOccurencesWasntInformed();

        int listOccurences = positionalFieldVO.getPositionalListVO().getOccurrences();
        List<Object> newList = new ArrayList<>(listOccurences);
        ParameterizedType parameterizedType = (ParameterizedType) currentJavaField.getGenericType();
        Class<?> classOfTheParameterizedType = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        IntStream.rangeClosed(ITERATOR_START_POSITIONAL, listOccurences).forEach(currentIndex -> {

            if (ConverterMapping.byType(classOfTheParameterizedType).isPresent()) {
                stopProcessingIfTheLengthWasntInformed();
                convertListOfPrimitiveValues(newList, classOfTheParameterizedType);
            } else {
                convertListOfComplexObjects(newList, classOfTheParameterizedType);
            }
        });
        return newList;
    }


    private void stopProcessingIfTheOccurencesWasntInformed() {
        if (positionalFieldVO.getPositionalListVO() == null || positionalFieldVO.getPositionalListVO().getOccurrences() == NOT_INFORMED) {
            throw new PositionalTweakException("To use lists, the usage of occurences attribute is mandatory");
        }
    }


    private void stopProcessingIfTheLengthWasntInformed() {
        if (positionalFieldVO.getLength() == NOT_INFORMED) {
            throw new PositionalTweakException("The attribute length is required for {}", currentJavaField.getName());
        }
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

    private Object convertComplexObject(Class<?> classOfTheParameterizedType) {
        try {

            Object complexObject =
                    classOfTheParameterizedType.getDeclaredConstructor().newInstance();

            Stream.of(classOfTheParameterizedType.getDeclaredFields()).forEach(field -> {
                PositionalFieldParser complexPositionalFieldParserInsideAList = new PositionalFieldParser(field, complexObject, positionalValue);
                Object valueForComplexObject = complexPositionalFieldParserInsideAList.buildFieldValue();
                complexPositionalFieldParserInsideAList.fillValue(valueForComplexObject);
            });
            return complexObject;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new PositionalTweakException("There was an error while filling the class {0}", e, classOfTheParameterizedType);
        }

    }

    public void fillFieldValue() {
        Object fieldValue = buildFieldValue();
        fillValue(fieldValue);
    }


    private Object convertValueAndRemoveUsedPositional(Class<?> type) {
        Optional<Converter> converter = ConverterMapping.byType(type);
        if (converter.isPresent()) {
            stopProcessingIfTheLengthWasntInformed();
            Object convertedValue = converter.get().fromPositional(positionalValue.substring(0, positionalFieldVO.getLength()), positionalFieldVO);
            positionalValue.delete(0, positionalFieldVO.getLength());
            return convertedValue;
        }
        return null;
    }

    private void fillValue(Object convertedValue) {
        try {
            Method setterMethod = currentInstance.getClass().getMethod(buildMethodName(currentJavaField, SET_PREFIX), currentJavaField.getType());
            setterMethod.invoke(currentInstance, convertedValue);
            return;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new PositionalTweakException("There was an error while trying to fill field {0} of class {1}");
        }
    }

    protected boolean isThisFieldMarkedForConvertion() {
        return currentJavaField.getAnnotation(PositionalEvict.class) == null;
    }

    protected boolean isThisAPrimitiveValue() {
        return ConverterMapping.byType(currentJavaField.getType()).isPresent();
    }

    protected boolean isThisFieldAList() {
        Class<?> type = currentJavaField.getType();
        return type.equals(List.class);
    }


    private String buildMethodName(Field field, String methodPrefix) {
        String fieldName = field.getName();
        StringBuilder stringBuilder = new StringBuilder()
                .append(methodPrefix).append(Character.toUpperCase(fieldName.charAt(FISRT_CHARACTER_BEGIN)))
                .append(fieldName.substring(FIRST_CHARACTER_END));
        return stringBuilder.toString();
    }


}
