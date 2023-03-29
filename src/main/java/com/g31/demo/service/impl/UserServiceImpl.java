package com.g31.demo.service.impl;

import com.g31.demo.exception.UserNameNotFoundException;
import com.g31.demo.service.UserService;
import com.g31.demo.model.User;
import com.g31.demo.repository.UserRepository;
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

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(RegisterRequest registerRequest) {
//        checkUserNameNotExist(userRegisterRequest.getUserName());
        User user = registerRequest.toUser();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        // TODO: If there are admin and normal user, more code should be added
    }

    @Override
    public void updateUser(UpdateRequest request){
        Optional<User> users = userRepository.findByUserName(request.getUserName());

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

        Optional<User> existingUserName = userRepository.findByUserName(user.getUserName());
        Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserName.isPresent() ||existingUserEmail.isPresent()){
            // TODO: How to throw exception.
//            throw new UserNameAlreadyExistException();
        }
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

    public void delete(String username){
        if(!userRepository.findByUserName(username).isPresent()){
            // TODO: throw an exception
        }
        userRepository.deleteByUserName(username);
    }

    // TODO: you may add more methods if needed

}
