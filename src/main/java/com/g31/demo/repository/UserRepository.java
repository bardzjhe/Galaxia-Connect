package com.g31.demo.repository;


import com.g31.demo.model.AuditUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Description: via用户名username or password查找用户，注意要按照JPA的格式使用camel case
 *
 * if we want more features, more could be added.
 */
public interface UserRepository extends JpaRepository<AuditUser, Long> {
    Optional<AuditUser> findByUserName(String username);
    Optional<AuditUser> findByEmail(String email);
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUserName(String userName);

    boolean existsByUserName(String username);

}
