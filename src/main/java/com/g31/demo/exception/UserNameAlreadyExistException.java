package com.g31.demo.exception;

import java.util.Map;

/**
 * @author shuang.kou
 */
public class UserNameAlreadyExistException extends BaseException {

    public UserNameAlreadyExistException(Map<String, String> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
