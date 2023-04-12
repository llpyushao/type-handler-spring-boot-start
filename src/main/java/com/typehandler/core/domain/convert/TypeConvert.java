package com.typehandler.core.domain.convert;

import com.typehandler.core.MetaDataMapping;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface TypeConvert {
    Map<String,Object> convertIfNecessaryMap(MetaDataMapping metaDataMappings, Field field, Object obj) throws IllegalAccessException;

    Map<String,Object> convertIfNecessaryMap(List<MetaDataMapping> metaDataMappings, Object obj) throws IllegalAccessException;

    String convertIfNecessary(List<MetaDataMapping> metaDataMappings, Object obj) throws IllegalAccessException;
}
