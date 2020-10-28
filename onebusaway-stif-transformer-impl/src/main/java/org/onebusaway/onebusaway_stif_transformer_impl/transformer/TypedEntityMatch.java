/**
 * Copyright (C) 2020 Metropolitan Transportation Authority
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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