package org.onebusaway.onebusaway_stif_transformer_impl.transformer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypedEntityMatch implements EntityMatch {
    private Class<?> _type;
    private Method _getMethod;
    private String _value;

    public TypedEntityMatch(Class<?> type, Method getMethod, String value) {
        _type = type;
        _getMethod = getMethod;
        _value = value;
    }

    public Class<?> getType() {
        return _type;
    }
    public boolean isApplicableToObject(Object object) {
        Class objectClass = object.getClass();
        try {
            if(objectClass.equals(_type)
                    && objectClass.getMethod(_getMethod.getName(),_getMethod.getParameterTypes()).equals(_getMethod)){
                String objectProperty = (String)_getMethod.invoke(object);
                if(objectProperty.matches(_value)) {
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}