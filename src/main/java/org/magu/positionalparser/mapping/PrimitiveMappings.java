package org.magu.positionalparser.mapping;

import java.util.HashSet;
import java.util.Set;

/**
 * Maps primitive types
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PrimitiveMappings 
{

    private static final Set<Class<?>> primimitiveTypes;
    
    static 
    {
        primimitiveTypes = new HashSet<>();
        primimitiveTypes.add(int.class);
        primimitiveTypes.add(long.class);
        primimitiveTypes.add(double.class);
        primimitiveTypes.add(Integer.class);
        primimitiveTypes.add(Long.class);
        primimitiveTypes.add(Double.class);
    }
    
    
    /**
     * Verify if the object's class is present in {@link #primimitiveTypes}
     * @param object Object to be verified
     * @return True for primitive type
     */
    public static boolean isPrimitive(Object object)
    {
        return primimitiveTypes.contains(object.getClass());
    }

}
