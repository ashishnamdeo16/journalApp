package com.crazym416.journalApp.service;

import com.crazym416.journalApp.entity.JournalEntry;
import com.crazym416.journalApp.entity.User;
import com.crazym416.journalApp.repository.JournalEntryRepository;
import com.crazym416.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // password is hashed here
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
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
