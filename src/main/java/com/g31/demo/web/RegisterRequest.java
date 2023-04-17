package com.g31.demo.web;


import com.g31.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * User register request format
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    public User toUser() {
        return User.builder().email(this.getEmail())
                .userName(this.getUserName())
                .enabled(true).build();
    }
}
