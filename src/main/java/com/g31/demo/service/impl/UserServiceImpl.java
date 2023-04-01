package com.g31.demo.service.impl;

import com.g31.demo.exception.RoleNotFoundException;
import com.g31.demo.exception.UserNameAlreadyExistException;
import com.g31.demo.exception.UserNameNotFoundException;
import com.g31.demo.model.Role;
import com.g31.demo.model.RoleType;
import com.g31.demo.model.UserRole;
import com.g31.demo.repository.RoleRepository;
import com.g31.demo.repository.UserRoleRepository;
import com.g31.demo.service.UserService;
import com.g31.demo.model.User;
import com.g31.demo.repository.UserRepository;
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UpdateRequest;
import com.g31.demo.web.UserRepresentation;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;
import java.util.Optional;

/**
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    public static final String USERNAME = "username:";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(RegisterRequest registerRequest) {
//        checkUserNameNotExist(userRegisterRequest.getUserName());
        User user = registerRequest.toUser();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        // save NORMAL user into the userRoleRepository
        Role studentRole = roleRepository.findByName(RoleType.USER.getRole())
                .orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getRole())));
        userRoleRepository.save(new UserRole(user, studentRole));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UpdateRequest request){
        Optional<User> users = userRepository.findByUserName(request.getUserName());

        if(!users.isPresent()){
//            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, users.get().getUsername()));
        }
        User user = users.get();
        if(Objects.nonNull(request.getUserName())){
            user.setUserName(request.getUserName());
        }
        if(Objects.nonNull(request.getPassword())){
            user.setPassword(request.getPassword());
        }

        if(Objects.nonNull(request.getEnabled())){
            user.setEnabled(request.getEnabled());
        }
        if(Objects.nonNull(request.getEmail())){
            user.setEmail(request.getEmail());
        }
        userRepository.save(user);
    }

    //TODO: If there is any others to check? like if username is legal.
    /**
     * Check both username or email
     *
     * @param user
     */
    @Override
    public void checkUserPresent(User user) {

        Optional<User> existingUserName = userRepository.findByUserName(user.getUsername());
        Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserName.isPresent() ||existingUserEmail.isPresent()){
            // TODO: How to throw exception.
//            throw new UserNameAlreadyExistException();
        }
    }

    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
    }
    public boolean checkPassword(String p1, String password){
        return this.bCryptPasswordEncoder.matches(p1, password);
    }

    // TODO: How to design exception
    public User findByUserName(String username){

        return null;
//        return userRepository.findByUserName(username)
//                .orElseThrow(() -> new UserNameNotFoundException());
    }

    public User find(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName)));
    }

    public void delete(String userName) {
        if (!userRepository.existsByUserName(userName)) {
            throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
        }
        userRepository.deleteByUserName(userName);
    }

    // TODO: you may add more methods if needed

}
