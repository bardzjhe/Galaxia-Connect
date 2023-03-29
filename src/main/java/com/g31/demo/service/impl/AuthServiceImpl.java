package com.g31.demo.service.impl;

import com.g31.demo.model.User;
import com.g31.demo.utils.CurrentUserUtils;
import com.g31.demo.utils.JwtTokenUtils;
import com.g31.demo.web.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 29/3/2023
 * @Description: Redis saves the token information (key:uid, value:token),
 * and deletes the token information in redis after logging out
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl {
    private final UserServiceImpl userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CurrentUserUtils currentUserUtils;

    public String createToken(LoginRequest request){
        User user = userService.findByUserName(request.getUsername());
        // 1. Check if password is correct
        if(!userService.checkPassword(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        // 2. Check if it's disabled.
        if(!user.isEnabled()){
            throw new BadCredentialsException("This account is disabled. ");
        }

        String token = JwtTokenUtils.createToken(user.getUserName(), Long.toString(user.getUid()), request.getRememberMe());
        stringRedisTemplate.opsForValue().set(Long.toString(user.getUid()), token);
        return token;
    }

    public void removeToken(){
        stringRedisTemplate.delete(Long.toString(currentUserUtils.getCurrentUser().getUid()));
    }

}
