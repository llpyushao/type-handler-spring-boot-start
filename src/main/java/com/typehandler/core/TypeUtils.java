package com.typehandler.core;

import java.lang.reflect.Field;
import java.util.List;

public class TypeUtils {
    public static Boolean isList(Field field){
        if(field.getType().equals(List.class)){
            return true;
        }
        return false;
    }

    public static List<Object> covertList(Field field,Object obj) throws IllegalAccessException {
        if(isList(field)){
            return (List<Object>)field.get(obj);
        }
        return null;
    }
}
