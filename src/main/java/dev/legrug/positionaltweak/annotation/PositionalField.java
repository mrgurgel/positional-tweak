package dev.legrug.positionaltweak.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PositionalField {


    /**
     * Complete field lenth (include dots, dashes, etc...)
     */
    int length() default -1;

    /**
     * Pattern to be applied on field's value, useful in date conversions.
     */
    String pattern() default "";
    
    /**
     * Indicates if the value should recieve {@link String#trim()} function.
     */
    boolean trim() default true;

    PositionalList listInfo() default @PositionalList(occurrences = -1);

    PositionalMonetary monetaryInfo() default @PositionalMonetary;

}
