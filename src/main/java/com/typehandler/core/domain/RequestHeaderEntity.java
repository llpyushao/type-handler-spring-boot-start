package com.typehandler.core.domain;

public class RequestHeaderEntity {
    private Long id;
    private Long groupID;
    private Long apiID;
    private String key;
    private String value;
    private String version;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public Long getApiID() {
        return apiID;
    }

    public void setApiID(Long apiID) {
        this.apiID = apiID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
