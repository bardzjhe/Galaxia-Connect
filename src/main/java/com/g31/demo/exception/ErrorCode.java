package com.g31.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Description:
 */

@Getter
public enum ErrorCode {
    USER_NAME_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "Username already exists"),
    Role_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "Role Not Found"),
    USER_NAME_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "User Not Found"),
    VERIFY_JWT_FAILED(1003, HttpStatus.UNAUTHORIZED, "Token Verification Failed"),
    METHOD_ARGUMENT_NOT_VALID(1003, HttpStatus.BAD_REQUEST, "Method Parameter Validation Failed");
    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
