package com.g31.demo.service;

import com.g31.demo.model.User;

import java.util.List;

/**
 * @Description:
 */
public interface UserService {
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);

}
