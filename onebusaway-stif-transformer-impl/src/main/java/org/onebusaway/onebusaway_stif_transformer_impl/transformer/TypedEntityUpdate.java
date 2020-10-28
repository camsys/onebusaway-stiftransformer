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
