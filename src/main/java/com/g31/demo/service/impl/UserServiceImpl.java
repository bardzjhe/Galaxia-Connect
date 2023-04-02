package com.g31.demo.service.impl;

import com.g31.demo.exception.UserNameNotFoundException;
import com.g31.demo.service.UserService;
<<<<<<< Updated upstream
import com.g31.demo.model.User;
import com.g31.demo.repository.UserRepository;
=======
import com.g31.demo.repository.AuditUserRepository;
>>>>>>> Stashed changes
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService{

<<<<<<< Updated upstream
    private final UserRepository userRepository;

=======
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuditUserRepository auditUserRepository;
    public static final String USERNAME = "username:";
>>>>>>> Stashed changes
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(RegisterRequest registerRequest) {
//        checkUserNameNotExist(userRegisterRequest.getUserName());
<<<<<<< Updated upstream
        User user = registerRequest.toUser();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
=======
        AuditUser auditUser = registerRequest.toUser();
        String encodedPassword = bCryptPasswordEncoder.encode(auditUser.getPassword());
        auditUser.setPassword(encodedPassword);
        auditUser.setEmail(auditUser.getEmail());
        auditUserRepository.save(auditUser);
        // save NORMAL user into the userRoleRepository
        Role studentRole = roleRepository.findByName(RoleType.USER.getName())
                .orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName())));
        userRoleRepository.save(new UserRole(auditUser, studentRole));
>>>>>>> Stashed changes

        // TODO: If there are admin and normal user, more code should be added
    }

    @Override
<<<<<<< Updated upstream
    public void updateUser(UpdateRequest request){
        Optional<User> users = userRepository.findByUserName(request.getUserName());
=======
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateRequest request){
        Optional<AuditUser> users = auditUserRepository.findByUserName(request.getUserName());
>>>>>>> Stashed changes

        if(!users.isPresent()){
            // TODO: to finish designing the exception
//            throw new UsernameNotFoundException();
        }
        User user = users.get();
        if(Objects.nonNull(request.getUserName())){
            user.setUserName(request.getUserName());
        }
        if(Objects.nonNull(request.getPassword())){
            user.setPassword(request.getPassword());
        }

        // TODO: check if role needed
//        if(Objects.nonNull(request.getRole())){
//
//        }
        if(Objects.nonNull(request.getEmail())){
            user.setEmail(request.getEmail());
        }
<<<<<<< Updated upstream
        userRepository.save(user);
=======
        auditUserRepository.save(auditUser);
>>>>>>> Stashed changes
    }

    //TODO: If there is any others to check? like if username is legal.
    /**
     * Check both username or email
     *
     * @param user
     */
    @Override
    public void checkUserPresent(User user) {

<<<<<<< Updated upstream
        Optional<User> existingUserName = userRepository.findByUserName(user.getUsername());
        Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
=======
        Optional<AuditUser> existingUserName = auditUserRepository.findByUserName(auditUser.getUserName());
        Optional<AuditUser> existingUserEmail = auditUserRepository.findByEmail(auditUser.getEmail());
>>>>>>> Stashed changes
        if(existingUserName.isPresent() ||existingUserEmail.isPresent()){
            // TODO: How to throw exception.
//            throw new UserNameAlreadyExistException();
        }
    }

<<<<<<< Updated upstream
=======
    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return auditUserRepository.findAll(PageRequest.of(pageNum, pageSize)).map(AuditUser::toUserRepresentation);
    }
>>>>>>> Stashed changes
    public boolean checkPassword(String p1, String password){
        return this.bCryptPasswordEncoder.matches(p1, password);
    }

    // TODO: How to design exception
    public User findByUserName(String username){

        return null;
//        return userRepository.findByUserName(username)
//                .orElseThrow(() -> new UserNameNotFoundException());
    }

<<<<<<< Updated upstream
    public void delete(String username){
        if(!userRepository.findByUserName(username).isPresent()){
            // TODO: throw an exception
        }
        userRepository.deleteByUserName(username);
=======
    public AuditUser find(String userName) {
        return auditUserRepository.findByUserName(userName).orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName)));
    }

    public void delete(String userName) {
        if (!auditUserRepository.existsByUserName(userName)) {
            throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
        }
        auditUserRepository.deleteByUserName(userName);
>>>>>>> Stashed changes
    }

    // TODO: you may add more methods if needed

}
