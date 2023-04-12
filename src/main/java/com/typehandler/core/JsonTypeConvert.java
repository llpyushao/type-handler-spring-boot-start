package com.typehandler.core;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class JsonTypeConvert extends DefaultTypeConvert{
    @Override
    public String convertIfNecessary(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }
}
