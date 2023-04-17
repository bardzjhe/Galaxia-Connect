package com.g31.demo.service;

import com.g31.demo.exception.RoleNotFoundException;
import com.g31.demo.exception.UserNameAlreadyExistException;
import com.g31.demo.exception.UserNameNotFoundException;
import com.g31.demo.model.Role;
import com.g31.demo.model.RoleType;
import com.g31.demo.model.User;
import com.g31.demo.model.UserRole;
import com.g31.demo.repository.RoleRepository;
import com.g31.demo.repository.UserRepository;
import com.g31.demo.repository.UserRoleRepository;
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UserRepresentation;
import com.g31.demo.web.UpdateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

/**
 * @author shuang.kou
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    public static final String USERNAME = "username:";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void save(RegisterRequest registerRequest) {
        ensureUserNameNotExist(registerRequest.getUserName());
        User user = registerRequest.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        // save NORMAL user into the userRoleRepository
        Role studentRole = roleRepository.findByName(RoleType.USER.getName()).orElseThrow(() -> new RoleNotFoundException(Map.of("roleName", RoleType.USER.getName())));
        userRoleRepository.save(new UserRole(user, studentRole));

    }

    public User find(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UserNameNotFoundException(Map.of(USERNAME, userName)));
    }

    public void update(UpdateRequest updateRequest) {
        User user = find(updateRequest.getUserName());
        if (Objects.nonNull(updateRequest.getEmail())) {
            user.setEmail(updateRequest.getEmail());
        }
        if (Objects.nonNull(updateRequest.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(updateRequest.getPassword()));
        }
        if (Objects.nonNull(updateRequest.getEnabled())) {
            user.setEnabled(updateRequest.getEnabled());
        }
        userRepository.save(user);
    }


    public void delete(String userName) {
        if (!userRepository.existsByUserName(userName)) {
            throw new UserNameNotFoundException(Map.of(USERNAME, userName));
        }
        userRepository.deleteByUserName(userName);
    }

    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
    }

    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    private void ensureUserNameNotExist(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new UserNameAlreadyExistException(Map.of(USERNAME, userName));
        }
    }
}
