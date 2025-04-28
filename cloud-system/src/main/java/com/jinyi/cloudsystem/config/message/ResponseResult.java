package com.jinyi.cloudsystem.config.message;

import com.jinyi.cloudsystem.constant.message.MessageCode;
import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;

    private String message;

    private T data;

    private long timestamp;

    public ResponseResult() {
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(MessageCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(MessageCode.SUCCESS.getCode(), MessageCode.SUCCESS.getMessage());
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(MessageCode.ERROR.getCode(), message);
    }
}
