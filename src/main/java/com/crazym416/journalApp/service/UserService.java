package com.crazym416.journalApp.service;

import com.crazym416.journalApp.entity.User;
import com.crazym416.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Remove because it is taken care by @Slf4j
   // private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void createUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword())); // password is hashed here
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
//            return true;
        }catch(Exception e){
            log.error("Cannot Create a New User");
//            return false;
        }

    }

    public void createAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // password is hashed here
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public boolean deleteUserById(ObjectId id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<User> getEntryById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }


}
