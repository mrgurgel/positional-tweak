package org.magu.positionalparser.converter;

import java.util.HashMap;
import java.util.Map;

import org.magu.positionalparser.converter.type.IntegerConverter;
import org.magu.positionalparser.converter.type.StringConverter;

/**
 * Register the available converter types
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class ConverterMapping 
{

    private static final Map<Class<?>, Converter<?>> loadedConverters = new HashMap<Class<?>, Converter<?>>();
    
    static
    {
        loadedConverters.put(String.class, new StringConverter());
        loadedConverters.put(Integer.class, new IntegerConverter());
    }
    
    public static Converter<?> findConverterFor(Class<?> clazz)
    {
        return loadedConverters.get(clazz);
    }
    
    
}
