package com.g31.demo.exception;

import java.util.Map;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 29/3/2023
 * @Description:
 */
public class UserNameNotFoundException extends BaseException{
    UserNameNotFoundException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }
}
