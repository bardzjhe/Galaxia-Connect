package com.g31.demo.service;

import com.g31.demo.model.User;
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UpdateRequest;

/**
 * @Description:
 */
public interface UserService {
    void saveUser(RegisterRequest request);
    void updateUser(UpdateRequest request);
    void checkUserPresent(User user);

}
