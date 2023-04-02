package com.g31.demo.exception;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 29/3/2023
 * @Description:
 */
public class UserNameNotFoundException extends BaseException{
    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }


}
