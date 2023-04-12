package com.typehandler.core;

public enum ExistSubEnum {
    YES(1,"存在"),
    NO(2,"不存在");

    private Integer value;
    private String name;

    ExistSubEnum(Integer value, String name){
        this.value=value;
        this.name=name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ExistSubEnum fromByte(Integer value) {
        for (ExistSubEnum item : ExistSubEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new TypeException("Unable to find target type");
    }

    public static String getName(Integer value) {
        return fromByte(value).getName();
    }
}
