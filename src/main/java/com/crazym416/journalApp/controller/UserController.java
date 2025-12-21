package com.crazym416.journalApp.controller;

import com.crazym416.journalApp.entity.User;
import com.crazym416.journalApp.service.QuotesService;
import com.crazym416.journalApp.service.UserDetailsServiceImpl;
import com.crazym416.journalApp.service.UserService;
import com.crazym416.journalApp.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuotesService quotesService;


//    @GetMapping
//    public ResponseEntity<?>  getAllUser(){
//        List<User> all = userService.getAll();
//        if(all != null && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return new ResponseEntity<>("Hi " + userName +  " Quote For You: " + quotesService.getQuote(),HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public  Optional<User> getUserById(@PathVariable ObjectId id){
        return userService.getEntryById(id);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.createUser(userInDb);
            return new ResponseEntity<>(userInDb,HttpStatus.OK);
    }
}
