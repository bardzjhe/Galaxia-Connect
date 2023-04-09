package com.g31.demo.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * user representation format
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    @NotBlank
    private String userName;
    private String password;
    private String fullName;
    private Boolean enabled;
}
