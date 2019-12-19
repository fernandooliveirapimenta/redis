package com.sample.controller;


import com.sample.dal.User;
import com.sample.dal.UserSprRepository;
import com.sample.service.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by OmiD.HaghighatgoO on 8/4/2019.
 */

@RestController
@RequestMapping("/api")
public class UserController {


    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    private UserSprRepository userSprRepository;

    @PostMapping
    public User save(@RequestBody User user) {
        return userSprRepository.save(user);
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable Long id){
       return userSprRepository.findById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userSprRepository.deleteById(id);
    }

    @GetMapping(path = "/find")
    public List<User> find(User user){

        if(user.getName() != null && user.getSurname() != null)
            return userSprRepository.findByNameAndSurname(user.getName(), user.getSurname());

        if(user.getName() != null) {
            return Collections.singletonList(authorizationService.findByName(user.getName()));
        }

        return new ArrayList<>();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {

        Boolean result = authorizationService.saveUser(user);
        if (result) {
            return ResponseEntity.ok("A new user is saved!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }

    }


    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public ResponseEntity<User> findUser(@RequestBody User User) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(User, User.class);

        User result = authorizationService.findByName(user.getName());

        return ResponseEntity.ok(result);


    }
}

