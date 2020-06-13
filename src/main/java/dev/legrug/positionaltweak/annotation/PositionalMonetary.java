package dev.legrug.positionaltweak.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PositionalMonetary {

    int numberOfDecimalPlaces() default -1;
    String monetaryPattern() default "";
    String decimalSeparator() default "";


}
