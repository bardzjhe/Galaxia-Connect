package com.g31.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g31.demo.web.UserRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;  // primary key

    @NotNull(message = "User name cannot be empty")
    @Column
    private String userName;

    @NotNull(message = "Password cannot be empty")
    @Length(message = "Password is suggested to be at least 8 characters long")
    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", unique = true)
    private String email;

    @Column(columnDefinition = "default 1")
    private Boolean enabled;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
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

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .userName(this.userName).build();
    }

}