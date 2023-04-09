package com.g31.demo.exception;

import java.util.Map;

/**
 * @author shuang.kou
 */
public class UserNameNotFoundException extends BaseException {
    public UserNameNotFoundException(Map<String, String> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
