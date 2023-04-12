package com.typehandler.core;

import com.typehandler.core.domain.convert.TypeConvert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TypeConvertFactory {
    private static final Map<String, TypeConvert> TYPE_CONVERT_MAP=new ConcurrentHashMap<>();

    private TypeConvertFactory(){
        TYPE_CONVERT_MAP.put("json",new JsonTypeConvert());
        TYPE_CONVERT_MAP.put("xml",new XMLTypeConvert());
    }

    public TypeConvert getTypeConvert(String key){
        TypeConvert typeConvert=TYPE_CONVERT_MAP.get(key);
        if (TYPE_CONVERT_MAP.get(key)!=null){
            return TYPE_CONVERT_MAP.get(key);
        }
        throw new TypeException("The target TypeConvert could not be found");
    }

    public static TypeConvertFactory of(){
        return new TypeConvertFactory();
    }
}
