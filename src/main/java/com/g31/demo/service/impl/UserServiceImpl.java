package com.g31.demo.service.impl;

import com.g31.demo.exception.RoleNotFoundException;
import com.g31.demo.exception.UserNameNotFoundException;
import com.g31.demo.model.AuditUser;
import com.g31.demo.model.Role;
import com.g31.demo.model.RoleType;
import com.g31.demo.model.UserRole;
import com.g31.demo.repository.RoleRepository;
import com.g31.demo.repository.UserRoleRepository;
import com.g31.demo.service.UserService;
import com.g31.demo.repository.UserRepository;
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UpdateRequest;
import com.g31.demo.web.UserRepresentation;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void save(RegisterRequest registerRequest) {
//        checkUserNameNotExist(userRegisterRequest.getUserName());
        AuditUser auditUser = registerRequest.toUser();
        String encodedPassword = bCryptPasswordEncoder.encode(auditUser.getPassword());
        auditUser.setPassword(encodedPassword);
        auditUser.setEmail(auditUser.getEmail());
        userRepository.save(auditUser);
        // save NORMAL user into the userRoleRepository
        Role studentRole = roleRepository.findByName(RoleType.USER.getName())
                .orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName())));
        userRoleRepository.save(new UserRole(auditUser, studentRole));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateRequest request){
        Optional<AuditUser> users = userRepository.findByUserName(request.getUserName());

        if(!users.isPresent()){
//            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, users.get().getUsername()));
        }
        AuditUser auditUser = users.get();
        if(Objects.nonNull(request.getUserName())){
            auditUser.setUserName(request.getUserName());
        }
        if(Objects.nonNull(request.getPassword())){
            auditUser.setPassword(request.getPassword());
        }

        if(Objects.nonNull(request.getEnabled())){
            auditUser.setEnabled(request.getEnabled());
        }
        if(Objects.nonNull(request.getEmail())){
            auditUser.setEmail(request.getEmail());
        }
        userRepository.save(auditUser);
    }

    //TODO: If there is any others to check? like if username is legal.
    /**
     * Check both username or email
     *
     * @param auditUser
     */
    @Override
    public void checkUserPresent(AuditUser auditUser) {

        Optional<AuditUser> existingUserName = userRepository.findByUserName(auditUser.getUserName());
        Optional<AuditUser> existingUserEmail = userRepository.findByEmail(auditUser.getEmail());
        if(existingUserName.isPresent() ||existingUserEmail.isPresent()){
            // TODO: How to throw exception.
//            throw new UserNameAlreadyExistException();
        }
    }

    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(AuditUser::toUserRepresentation);
    }
    public boolean checkPassword(String p1, String password){
        return this.bCryptPasswordEncoder.matches(p1, password);
    }

    // TODO: How to design exception
    public AuditUser findByUserName(String username){

        return null;
//        return userRepository.findByUserName(username)
//                .orElseThrow(() -> new UserNameNotFoundException());
    }

    public AuditUser find(String userName) {
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
