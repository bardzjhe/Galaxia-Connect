package com.g31.demo.controller;


import com.g31.demo.service.impl.UserServiceImpl;
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UpdateRequest;
import com.g31.demo.web.UserRepresentation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description: User controller is used for handling
 * 1. user's getting other user usernames request
 * 2. admin's updating and deleting user's info request
 * 3. Both sign up request.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "user")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/sign-up")
    @ApiOperation("User sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid RegisterRequest request) {
        userService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @ApiOperation("Get all user name in pages")
    public ResponseEntity<Page<UserRepresentation>> getAllUser(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
        return ResponseEntity.ok().body(allUser);
    }
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation("Update user")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateRequest request) {
        userService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation("delete user")
    public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
        userService.delete(username);
        return ResponseEntity.ok().build();
    }
}
