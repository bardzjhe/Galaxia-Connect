package com.g31.demo.exception;

import java.util.Map;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 29/3/2023
 * @Description:
 */
public class UserNameAlreadyExistException extends BaseException{
    UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
