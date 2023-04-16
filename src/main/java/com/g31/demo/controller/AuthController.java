package com.g31.demo.controller;

import com.g31.demo.common.SecurityConst;
import com.g31.demo.service.AuthService;
import com.g31.demo.web.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author shuang.kou
 * @description 认证授权
 **/
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "Authentication")
public class AuthController {

    private final AuthService authService;
    private final String[] cmd = {"cmd", "/resources/galaxy/", "node server.js"};

    @PostMapping("/login")
    @ApiOperation("Login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) throws IOException, InterruptedException {
        String token = authService.createToken(loginRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConst.TOKEN_HEADER, token);
        System.out.println("here 1");
//        Runtime.getRuntime().exec(cmd);

        Runtime.getRuntime().exec("1.bat");

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ApiOperation("Logout")
    public ResponseEntity<Void> logout() {
        System.out.println("here 2");
        authService.removeToken();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
