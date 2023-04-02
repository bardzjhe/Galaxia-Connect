package com.g31.demo.service;

import com.g31.demo.web.LoginRequest;

/**
 * @Description:
 */
public interface AuthService {
    String createToken(LoginRequest request);

}
