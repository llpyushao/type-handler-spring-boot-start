package com.typehandler.core;

import com.typehandler.core.domain.TypeConversionEntity;

public abstract class BaseTypeHandler<T, R> extends TypeReference<T> implements TypeHandler<T, R> {
    @Override
    public R typeConversion(T parameter) {
        if (parameter == null) {
            throw new TypeException("parameter value must be specified.");
        }
        try {
            return setNonNullParameter(parameter);
        } catch (TypeException ex) {
            throw new TypeException(String.format("Error setting null for parameter type [%s] with source type , Cause:[%s] ", parameter.getClass().getTypeName(), ex.getMessage()), ex);
        }
    }

    public abstract R setNonNullParameter(T parameter) throws TypeException;

    @Override
    public R typeConversion(TypeConversionEntity<T> parameter) {
        if (parameter == null) {
            throw new TypeException("Typecast entity must be specified.");
        }
        if (parameter.getValue() == null) {
            throw new TypeException("The value in the type conversion entity must be specified.");
        }
        if (parameter.getTypeReference() == null) {
            throw new TypeException("The typeReference in the type conversion entity must be specified.");
        }
        try {
            return setNonNullParameter(parameter);
        } catch (TypeException ex) {
            throw new TypeException(String.format("Error setting null for parameter type [%s] with source type , Cause:[%s] ", parameter.getTypeReference().getType().getTypeName(), ex.getMessage()), ex);
        }
    }

    public abstract R setNonNullParameter(TypeConversionEntity<T> parameter) throws TypeException;
}
