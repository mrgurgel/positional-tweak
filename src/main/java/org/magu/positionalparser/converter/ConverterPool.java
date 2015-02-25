package org.magu.positionalparser.converter;

import java.util.HashMap;
import java.util.Map;

import org.magu.positionalparser.converter.type.StringConverter;

public class ConverterPool 
{

    private static final Map<Class<?>, Converter<?>> loadedConverters = new HashMap<Class<?>, Converter<?>>();
    
    static
    {
        loadedConverters.put(String.class, new StringConverter());
    }
    
    public static Converter<?> findConverterFor(Class<?> clazz)
    {
        
        return loadedConverters.get(clazz);
    }
    
    
}
