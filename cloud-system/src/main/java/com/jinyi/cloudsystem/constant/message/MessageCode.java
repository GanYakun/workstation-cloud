package com.jinyi.cloudsystem.constant.message;

import lombok.Getter;

@Getter
public enum MessageCode {

    SUCCESS(200, "成功"),

    //业务报错时，返回该状态码
    ERROR(500, "服务器错误,请查看详细报错"),

    NOT_FOUND(404, "未找到"),

    UNAUTHORIZED(401, "未授权"),

    FORBIDDEN(403, "禁止访问"),

    NOT_ACCEPTABLE(406, "请求格式错误"),

    CONFLICT(409, "冲突"),

    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),

    UNPROCESSABLE_ENTITY(422, "无法处理");

    private final String message;

    private final int code;

    MessageCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
