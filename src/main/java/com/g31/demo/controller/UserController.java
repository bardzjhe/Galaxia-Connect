package com.g31.demo.controller;

import com.g31.demo.service.UserService;
import com.g31.demo.web.RegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ApiOperation("User sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid RegisterRequest request) {
        userService.saveUser(request);
        return ResponseEntity.ok().build();
    }

    // TODO: if the user is admin, he/she should be capable of obtaining all information
    // TODO: some methods can be added for admin, like update user's information or delete.

}
