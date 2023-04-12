package com.typehandler.core;

import com.typehandler.core.domain.ResultTestEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DefaultRequestHandler implements RequestHandler {

    @Override
    public Object postHandler(List<MetaDataMapping> metaDataMappings, ResultTestEntity resultTestEntity, Map<String, List<String>> heandMap, String url) throws IOException, IllegalAccessException {
        return OkHttpUtil.getInstance().postJson(url, TypeConvertFactory.of().getTypeConvert("json").convertIfNecessary(metaDataMappings, resultTestEntity));
    }
}
