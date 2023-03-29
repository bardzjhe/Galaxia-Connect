package com.g31.demo.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRepresentation {
    private String userName;
}
