package org.onebusaway.onebusaway_stif_transformer_impl.transformer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypedEntityUpdate extends TypedEntityMatch{
    Method _setMethod;
    private String _value;
    Method _getMethod;
    private Class<?> _type;

    public TypedEntityUpdate(Class<?> type, Method getMethod, Method setMethod, String value) {
        super(type, getMethod, value);
        _setMethod = setMethod;
        _value = value;
        _getMethod = getMethod;
        _type = type;
    }


    public Class<?> getType() {
        return super.getType();
    }
    public boolean isApplicableToObject(Object object) {
        try {
            if (object.getClass().equals(_type)){
                _setMethod.invoke(object, _value);
                if (_getMethod.invoke(object)==_value) {
                    return true;
                }

            }
        }
        catch (IllegalAccessException | InvocationTargetException e){
            return false;
        }
        return false;
    }
}
