package com.g31.demo.service.impl;

import com.g31.demo.model.AuditUser;
import com.g31.demo.model.JwtUser;
import com.g31.demo.service.AuthService;
import com.g31.demo.utils.CurrentUserUtils;
import com.g31.demo.utils.JwtTokenUtils;
import com.g31.demo.web.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 29/3/2023
 * @Description: Redis saves the token information (key:uid, value:token),
 * and deletes the token information in redis after logging out
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CurrentUserUtils currentUserUtils;

    @Override
    public String createToken(LoginRequest request){
        AuditUser auditUser = userService.findByUserName(request.getUsername());
        // 1. checks if the provided password matches the password stored for the user.
        if(!userService.checkPassword(request.getPassword(), auditUser.getPassword())){
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        JwtUser jwtUser = new JwtUser(auditUser);
        // 2. checks if the user is enabled.
        if(!jwtUser.isEnabled()){
            throw new BadCredentialsException("This account is disabled. ");
        }

        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // creates a JWT using JwtTokenUtils.createToken() method, passing the user's username, UID,
        // and a boolean parameter indicating if the user wants to be remembered after logging in.
        String token = JwtTokenUtils.createToken(auditUser.getUserName(),
                Long.toString(auditUser.getUid()), authorities, request.getRememberMe());
        // saves the UID and the JWT token to the Redis cache
        stringRedisTemplate.opsForValue().set(Long.toString(auditUser.getUid()), token);
        return token;
    }

    public void removeToken(){
        stringRedisTemplate.delete(Long.toString(currentUserUtils.getCurrentUser().getUid()));
    }

}
