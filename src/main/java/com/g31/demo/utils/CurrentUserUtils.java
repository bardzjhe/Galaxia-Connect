package com.g31.demo.utils;

import com.g31.demo.model.User;
import com.g31.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 29/3/2023
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserUtils {
    private final UserServiceImpl userService;

    public User getCurrentUser(){
        return userService.findByUserName(getCurrentUserName());
    }
    private String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}