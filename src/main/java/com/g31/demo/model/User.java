package com.g31.demo.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 */


@Table(name = "user")
@Entity
public class User {
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
    @Column(name = "user_name")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Length(min = 8, message = "Password should be at least 8 characters long")
    @Column(name = "password")
    private String password;
    @OneToMany
    private List<User> friendList;


    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}