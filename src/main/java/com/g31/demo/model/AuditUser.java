package com.g31.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g31.demo.web.UserRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description: Our application will allow new users to register and login to our application.
 * Every User will have only one role. The role associated with a user will be used in future to decide
 * whether the user is authorized to access a particular resource on our server or not.
 *
 * TODO: 决定用户的好友, 一般user/admin详情
 */
@Table(name = "auditUser")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditUser extends AuditBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;  // primary key

//    @NotNull(message = "User name cannot be empty")
    @Column(nullable = false)
    private String userName;

//    @NotNull(message = "Password cannot be empty")
    @Length(message = "Password is suggested to be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "auditUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

//    @NotNull(message = "Email cannot be empty")

    @Email(message = "Please enter a valid email address")
    @Column
    private String email;

    @Column
    private Boolean enabled;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .userName(this.userName).build();
    }

}