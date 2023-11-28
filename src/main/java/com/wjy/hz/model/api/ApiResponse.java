package com.wjy.hz.model.api;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String code;
    private String message;
    private T data;

    // 预定义的常用响应对象，避免重复创建
    private static final String FORBIDDEN_RESPONSE = buildResponseJson(ApiResponseCodeEnum.RESOURCE_FORBIDDEN);
    private static final String INTERFACE_FORBIDDEN_RESPONSE = buildResponseJson(ApiResponseCodeEnum.INTERFACE_FORBIDDEN);
    private static final String RESOURCE_NOT_FOUND_RESPONSE = buildResponseJson(ApiResponseCodeEnum.RESOURCE_NOT_FOUND);
    private static final String INTERFACE_ERROR_RESPONSE = buildResponseJson(ApiResponseCodeEnum.INTERFACE_ERROR);
    private static final String SYSTEM_ERROR_RESPONSE = buildResponseJson(ApiResponseCodeEnum.SYSTEM_ERROR);

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private static String buildResponseJson(ApiResponseCodeEnum codeEnum) {
        ApiResponse<Object> res = new ApiResponse<>(codeEnum.getCode(), codeEnum.getMessage());
        return JSON.toJSONString(res);
    }

    public static <T> String ok(T data) {
        return JSON.toJSONString(new ApiResponse<>(ApiResponseCodeEnum.SUCCESS.getCode(), ApiResponseCodeEnum.SUCCESS.getMessage(), data));
    }

    public static String resForbidden() {
        return FORBIDDEN_RESPONSE;
    }

    public static String intForbidden() {
        return INTERFACE_FORBIDDEN_RESPONSE;
    }

    public static String resNotFound() {
        return RESOURCE_NOT_FOUND_RESPONSE;
    }

    public static String intError() {
        return INTERFACE_ERROR_RESPONSE;
    }

    public static String sysError() {
        return SYSTEM_ERROR_RESPONSE;
    }
}
