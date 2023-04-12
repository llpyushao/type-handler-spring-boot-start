package com.typehandler.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class ResultTestEntity<T> {
    private String appKey;
    private String token;
    private T data;
    private String signature;
}
