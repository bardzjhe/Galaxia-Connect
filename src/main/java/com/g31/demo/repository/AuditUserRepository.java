package com.g31.demo.repository;


import com.g31.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< Updated upstream:src/main/java/com/g31/demo/repository/UserRepository.java
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> Stashed changes:src/main/java/com/g31/demo/repository/AuditUserRepository.java

import java.util.List;
import java.util.Optional;

/**
 * @Description: via用户名username or password查找用户，注意要按照JPA的格式使用camel case
 *
 * if we want more features, more could be added.
 */
<<<<<<< Updated upstream:src/main/java/com/g31/demo/repository/UserRepository.java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    User findByUserNameAndPassword(String username, String password);
    User findUserByUid(long uid);
    Optional<User> findByEmail(String email);
    void deleteByUserName(String username);
=======
@Repository
public interface AuditUserRepository extends JpaRepository<AuditUser, Long> {
    Optional<AuditUser> findByUserName(String username);
    Optional<AuditUser> findByEmail(String email);
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUserName(String userName);

    boolean existsByUserName(String username);

>>>>>>> Stashed changes:src/main/java/com/g31/demo/repository/AuditUserRepository.java
}
