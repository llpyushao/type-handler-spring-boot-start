package com.typehandler.core;

import lombok.Data;

import java.util.List;

@Data
public class MetaDataMapping {
    private Long id;
    private Long groupID;
    private Long apiID;
    private Integer leval;
    private Integer maxLeval;
    private Long paramentID;
    //业务类型 (如:订单/库存)
    private Integer bizType;
    private String bizTypeName;
    //映射类型 (如:主表映射/明细映射)
    private Integer bizMappingType;
    //本地列名
    private String originColumnName;
    //本地字段数据类型
    private Integer originDataType;
    //本地字段说明
    private String originColumnComment;
    //本地字段默认值
    private String originColumnDefault;
    //目标列名
    private String targetColumnName;
    //目标列说明
    private String targetColumnComment;
    //目标列数据类型
    private Integer targetDataType;
    //目标列默认值
    private String targetColumnDefault;
    //扩展函数转换
    private String functionHandler;
    private String version;
    private String author;
    //分割符
    private String splitSymbol;
    //子类
    private List<MetaDataMapping> subMetaDataMapping;
    //是否存在子级
    private Integer existSub;
}
