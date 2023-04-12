package com.typehandler.core.domain;

import com.typehandler.core.TypeReference;

public class TypeConversionEntity<T> {
    private T value;
    private String splitSymbol;
    private TypeReference<T> typeReference;

    public TypeConversionEntity() {
    }

    public TypeConversionEntity(T value, String splitSymbol, TypeReference<T> typeReference) {
        this.value = value;
        this.splitSymbol = splitSymbol;
        this.typeReference = typeReference;
    }

    public TypeConversionEntity(TypeReference<T> typeReference){
        this.typeReference=typeReference;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getSplitSymbol() {
        return splitSymbol;
    }

    public void setSplitSymbol(String splitSymbol) {
        this.splitSymbol = splitSymbol;
    }

    public TypeReference<T> getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }
}
