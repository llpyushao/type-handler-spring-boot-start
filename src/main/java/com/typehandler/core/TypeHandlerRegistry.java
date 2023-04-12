package com.typehandler.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TypeHandlerRegistry {
    private final Map<String, TypeHandler> typeHandlerMap = new ConcurrentHashMap<>();
    private final Map<Class<?>, TypeHandler> classTypeHandlerMap = new ConcurrentHashMap<>();
    private final static TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    private TypeHandlerRegistry() {
        register(String.class.getSimpleName(), new StringTypeHandler<>());
        register(String.class, new StringTypeHandler<>());
    }

    public <T, R> void register(String typeName, TypeHandler<T, R> typeHandler) {
        typeHandlerMap.put(typeName, typeHandler);
    }

    public <T, R> void register(Class<?> clazz, TypeHandler<T, R> typeHandler) {
        classTypeHandlerMap.put(clazz, typeHandler);
    }

    public TypeHandler getTypeHandler(String javaTypeName) {
        if(typeHandlerMap.get(javaTypeName)!=null){
            return typeHandlerMap.get(javaTypeName);
        }
        throw new TypeException("The target TypeHandler could not be found");
    }

    public TypeHandler getTypeHandler(Class<?> clazz) {
        if(classTypeHandlerMap.get(clazz)!=null){
            return classTypeHandlerMap.get(clazz);
        }
        throw new TypeException("The target TypeHandler could not be found");
    }

    public static TypeHandlerRegistry of() {
        return typeHandlerRegistry;
    }
}
