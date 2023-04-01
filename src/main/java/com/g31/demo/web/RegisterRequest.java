package com.g31.demo.web;

import com.g31.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 *
 * http://localhost:8080/api/users/sign-up
 * {"userName":"123456","password":"123456", "email":"@outlook.com"}
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
        return User.builder().userName(this.getUserName())
                .email(this.getEmail())
                .enabled(true).build();

    }
}
