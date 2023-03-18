package com.g31.demo.repository;


import com.g31.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: via用户名username or password查找用户，注意要按照JPA的格式使用camel case
 *
 * if we want more features, more could be added.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

}
