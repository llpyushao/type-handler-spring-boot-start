package com.typehandler.core;

import com.google.common.base.Joiner;
import com.typehandler.core.domain.TypeConversionEntity;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StringTypeHandler<T> extends BaseTypeHandler<T, String> {
    @Override
    public String setNonNullParameter(T parameter) throws TypeException {
        return String.valueOf(parameter);
    }

    @Override
    public String setNonNullParameter(TypeConversionEntity<T> parameter) throws TypeException {
        Type type = parameter.getTypeReference().getType();
        if (type instanceof Class) {
            return cast(parameter.getValue(), (Class<T>) type, parameter.getSplitSymbol());
        }
        if (type instanceof ParameterizedType) {
            return cast(parameter.getValue(), (ParameterizedType) type, parameter.getSplitSymbol());
        }
        throw new TypeException(String.format("can not cast to :[%]", type));
    }

    private String cast(Object obj, ParameterizedType type, String splitSymbol) {
        Type rawTye = type.getRawType();
        if ((rawTye == List.class || rawTye == ArrayList.class)) {
            return setValue(obj, splitSymbol);
        }
        throw new TypeException(String.format("can not cast to :[%]", type));
    }

    private String setValue(Object obj, String splitSymbol) {
        if (StringUtils.isEmpty(splitSymbol)) {
            throw new TypeException("The splitSymbol in the type conversion entity must be specified.");
        }
        return Joiner.on(splitSymbol).join((List)obj);
    }

    private String cast(Object obj, final Class<T> clazz, String splitSymbol) {
        if (clazz.isArray()) {
            Object[] objects = (Object[]) obj;
            List<String> list = new ArrayList<>();
            for (Object item : objects) {
                list.add(item.toString());
            }
            return setValue(list, splitSymbol);
        }
        return String.valueOf(obj);
    }
}
