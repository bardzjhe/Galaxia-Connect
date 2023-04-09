package com.g31.demo.exception;

import java.util.Map;


public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(Map<String, String> data) {
        super(ErrorCode.Role_NOT_FOUND, data);
    }
}
