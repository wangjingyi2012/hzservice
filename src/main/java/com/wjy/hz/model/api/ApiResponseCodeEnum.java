package com.wjy.hz.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiResponseCodeEnum {

    SUCCESS("00000", "success"),
    RESOURCE_FORBIDDEN("00401", "resource forbidden"),
    INTERFACE_FORBIDDEN("00403", "interface forbidden"),
    RESOURCE_NOT_FOUND("00404", "resource is not exist"),
    INTERFACE_ERROR("00405", "interface error"),
    SYSTEM_ERROR("00500", "system error");

    private final String code;

    private final String message;

}