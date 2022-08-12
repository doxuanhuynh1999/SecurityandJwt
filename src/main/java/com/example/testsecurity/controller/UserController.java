package com.example.testsecurity.controller;

import com.example.testsecurity.entity.User;
import com.example.testsecurity.payload.LoginRequest;
import com.example.testsecurity.payload.LoginResponse;
import com.example.testsecurity.payload.RandomStuff;
import com.example.testsecurity.service.UserService;
import com.example.testsecurity.service.UserServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/add")
    public ResponseEntity<Object> add(@RequestBody User user){
        User user2 = new User(user.getUsername(), user.getPassword());
        userService.add(user2);
        return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
    }
    @GetMapping("/update/{id}")
    User update(@RequestBody User user, @PathVariable("id") Long id) {
        return userService.update(user, id);
    }
    @GetMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
    @GetMapping("/login")
    public LoginResponse authenticateUsers(@RequestBody LoginRequest loginRequest) {
        return userServiceImpl.authenticateUser(loginRequest);
    }
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT hợp lệ");
    }


}
