package com.g31.demo.web;

import com.g31.demo.model.Role;
import com.g31.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private Role role;
    @NotBlank
    private String email;

    public User toUser() {
        return User.builder().userName(this.getUserName())
                .email(this.getEmail())
                .role(this.getRole())
                .enabled(true).build();
    }
}
