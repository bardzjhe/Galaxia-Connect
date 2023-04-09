package com.g31.demo.web;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author shuang.kou
 * @description User login request format
 */
@Data
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private Boolean rememberMe;
}
