package com.typehandler.core;

import com.typehandler.core.domain.TypeConversionEntity;
import com.typehandler.core.domain.convert.TypeConvert;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public abstract class DefaultTypeConvert implements TypeConvert {
    @Override
    public Map<String,Object> convertIfNecessaryMap(MetaDataMapping metaDataMappings, Field field,Object obj) throws IllegalAccessException{
        Map<String,Object> map=new HashMap<>();
        TypeConversionEntity typeConversionEntity=FieldTypeEnum.getTypeConversionEntity(metaDataMappings.getOriginDataType());
        typeConversionEntity.setValue(field.get(obj));
        typeConversionEntity.setSplitSymbol(metaDataMappings.getSplitSymbol());
        map.put(metaDataMappings.getTargetColumnName(),
                FieldTypeEnum.getTypeHandler(metaDataMappings.getTargetDataType()).typeConversion(typeConversionEntity));
        return map;
    }

    @Override
    public Map<String,Object> convertIfNecessaryMap(List<MetaDataMapping> metaDataMappings, Object obj) throws IllegalAccessException {
        if(obj==null){
            throw new TypeException("The original data cannot be empty");
        }
        if(CollectionUtils.isEmpty(metaDataMappings)){
            throw new TypeException("Transformation data cannot be empty");
        }
        Map<String,Object> map=new HashMap<>();
        Set<Field> fields= ReflectionUtils.getFields(obj.getClass());
        for(MetaDataMapping item:metaDataMappings){
            Optional<Field> field= fields.stream().filter(n->n.getName().equals(item.getOriginColumnName())).findFirst();
            if(!field.isPresent()){
                throw new TypeException(String.format("Business type [%s] field [%s] does not exist",item.getBizTypeName(),item.getOriginColumnName()));
            }
            field.get().setAccessible(true);
            if(ExistSubEnum.YES.getValue().equals(item.getExistSub())){
                if(CollectionUtils.isEmpty(item.getSubMetaDataMapping())){
                    throw new TypeException("The data maintenance is incorrect, the existing child data is empty");
                }
                if(TypeUtils.isList(field.get())){
                    List<Map<String,Object>> nodeList=new ArrayList<>();
                    for(Object node:TypeUtils.covertList(field.get(),obj)){
                        nodeList.add(convertIfNecessaryMap(item.getSubMetaDataMapping(),node));
                    }
                    map.put(item.getTargetColumnName(),nodeList);
                    continue;
                }
                map.put(item.getTargetColumnName(),convertIfNecessaryMap(item.getSubMetaDataMapping(),field.get().get(obj)));
                continue;
            }
            map.putAll(convertIfNecessaryMap(item,field.get(),obj));
        }
        if(MapUtils.isNotEmpty(map)){
            return map;
        }
        throw new TypeException("Can't convert any data");
    }

    @Override
    public String convertIfNecessary(List<MetaDataMapping> metaDataMappings, Object obj) throws IllegalAccessException {
        Map<String,Object> map=convertIfNecessaryMap(metaDataMappings,obj);
        return convertIfNecessary(map);
    }

    public abstract String convertIfNecessary(Map<String,Object> map);
}
