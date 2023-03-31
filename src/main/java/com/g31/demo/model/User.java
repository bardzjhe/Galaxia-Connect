package com.g31.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;


/**
 * @Description: Our application will allow new users to register and login to our application.
 * Every User will have only one role. The role associated with a user will be used in future to decide
 * whether the user is authorized to access a particular resource on our server or not.
 *
 * TODO: 决定用户的好友, 一般user/admin详情
 */
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username" // unique username
        }),
        @UniqueConstraint(columnNames = {
                "email" // unique email
        })
})
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private long uid;  // primary key

    @NotNull(message = "User name cannot be empty")
    @Column(name = "username")
    private String userName;

    @NotNull(message = "Password cannot be empty")
    @Length(min = 8, message = "Password should be at least 8 characters long")
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", unique = true)
    private String email;

    @Column(columnDefinition = "default 1")
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


}