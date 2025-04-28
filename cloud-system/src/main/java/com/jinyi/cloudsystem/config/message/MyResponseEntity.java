
package com.jinyi.cloudsystem.config.message;

import com.jinyi.cloudsystem.constant.message.MessageCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MyResponseEntity<T> extends ResponseEntity<ResponseResult<T>> {

    public MyResponseEntity(HttpStatus status) {
        super(status);
    }

    public static <T> ResponseEntity<ResponseResult<T>> success() {
        return ResponseEntity.ok(ResponseResult.success());
    }

    public static <T> ResponseEntity<ResponseResult<T>> success(T data) {
        return ResponseEntity.ok(ResponseResult.success(data));
    }

    public static <T> ResponseEntity<ResponseResult<T>> success(String message, T data) {
        return ResponseEntity.ok(ResponseResult.success(message, data));
    }

    public static <T> ResponseEntity<ResponseResult<T>> error(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseResult.error(message));
    }

    public static <T> ResponseEntity<ResponseResult<T>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseResult<>(MessageCode.NOT_FOUND.getCode(), message));
    }

    public static <T> ResponseEntity<ResponseResult<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseResult.success("Created", data));
    }
}