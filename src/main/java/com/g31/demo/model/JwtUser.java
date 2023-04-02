package com.g31.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 1/4/2023
 * @Description:
 */
public class JwtUser implements UserDetails {
    private Long uid;
    private String username;
    private String email;
    private String password;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {}


    public JwtUser(AuditUser auditUser) {
        this.uid = auditUser.getUid();
        this.username = auditUser.getUserName();
        this.password = auditUser.getPassword();
        this.email = auditUser.getEmail();
        // by default, it's enabled
        if(auditUser.getEnabled() == null){
            this.enabled = true;
        }else{
            this.enabled = auditUser.getEnabled();
        }
        this.authorities = auditUser.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
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

    @Override
    public String toString() {
        return "JwtUser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
