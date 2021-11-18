package dev.legrug.positionaltweak.converter;

import dev.legrug.positionaltweak.converter.type.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Register the available converter types
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PrimitiveFieldsElegibleForConvertion
{

    private static final Map<Class<?>, Converter<?>> loadedConverters = new HashMap<Class<?>, Converter<?>>();
    private PrimitiveFieldsElegibleForConvertion() {}
    
    static
    {
        loadedConverters.put(String.class, new StringConverter());
        loadedConverters.put(Integer.class, new IntegerConverter());
        loadedConverters.put(Long.class, new LongConverter());
        loadedConverters.put(Date.class, new DateConverter());
        loadedConverters.put(BigDecimal.class, new BigDecimalConverter());
    }
    
    public static Optional<Converter> byType(Class<?> clazz)
    {
        return Optional.ofNullable(loadedConverters.get(clazz));
    }
    
    
}
