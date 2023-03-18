package com.g31.demo.Service;

import com.g31.demo.model.User;

/**
 * @Description:
 */
public interface UserService {

    /**
     * Login Service
     * @param username
     * @param password
     * @return
     */
    User loginService(String username, String password);

    /**
     * Register Service
     * @param user 要注册的User对象，属性中主键uid要为空，若uid不为空可能会覆盖已存在的user
     * @return
     */
    User registerService(User user);
}
