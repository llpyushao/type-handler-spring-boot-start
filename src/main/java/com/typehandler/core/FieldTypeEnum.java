package com.typehandler.core;

import com.typehandler.core.domain.TypeConversionEntity;

import java.util.ArrayList;
import java.util.List;

public enum FieldTypeEnum {
    INTEGER(1, Integer.class.getSimpleName(), new TypeConversionEntity<Integer>(new TypeReference<Integer>() {}){}),
    STRING(2,String.class.getSimpleName(), new TypeConversionEntity<String>(new TypeReference<String>() {}) {}),
    LIST(3, List.class.getSimpleName(), new TypeConversionEntity<List<Object>>(new TypeReference<List<Object>>() {}) {}),
    ARRAYLIST(4, ArrayList.class.getSimpleName(), new TypeConversionEntity<ArrayList<Object>>(new TypeReference<ArrayList<Object>>() {}) {});

    private Integer value;
    private String name;
    private TypeConversionEntity typeConversionEntity;

    FieldTypeEnum(Integer value, String name, TypeConversionEntity typeConversionEntity){
        this.value=value;
        this.name=name;
        this.typeConversionEntity=typeConversionEntity;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public TypeConversionEntity getTypeConversionEntity() {
        return typeConversionEntity;
    }

    public static FieldTypeEnum fromByte(Integer value) {
        for (FieldTypeEnum item : FieldTypeEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new TypeException("Unable to find target type");
    }

    public static String getName(Integer value) {
        return fromByte(value).getName();
    }

    public static TypeHandler getTypeHandler(Integer value){
        return TypeHandlerRegistry.of().getTypeHandler(getName(value));
    }

    public static TypeConversionEntity getTypeConversionEntity(Integer value){
        return fromByte(value).getTypeConversionEntity();
    }
}
