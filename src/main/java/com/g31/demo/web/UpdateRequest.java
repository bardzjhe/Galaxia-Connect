package com.g31.demo.web;

import com.g31.demo.model.RoleType;
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
public class UpdateRequest {
    @NotBlank
    private String userName;

    private String password;

    private Boolean enabled;

    private String email;
}
