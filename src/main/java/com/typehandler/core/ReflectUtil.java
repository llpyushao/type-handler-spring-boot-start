package com.typehandler.core;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class ReflectUtil {
    /**
     * 根据属性，拿到set方法，并把值set到对象中
     */
    public static void setValue(String filedName, Object value, Method[] methods,Object obj) throws InvocationTargetException, IllegalAccessException {
        String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        for(Method item:methods){
            if(item.getName().equals(methodName)){
                item.invoke(obj,value);
            }
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MetaDataMapping metaDataMapping=new MetaDataMapping();
        Set<Field> fields= ReflectionUtils.getFields(metaDataMapping.getClass());
        Set<Method> methods= ReflectionUtils.getMethods(metaDataMapping.getClass());
        String filedName= "groupID";
        String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        for(Method item:methods){
            if(item.getName().equals(methodName)){
                item.invoke(metaDataMapping,11229L);
            }
        }
        System.out.printf("");
    }
}
