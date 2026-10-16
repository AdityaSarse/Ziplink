package com.demo.controller;

import com.demo.dtos.LoginRequest;
import com.demo.dtos.RegisrerRequest;
import lombok.AllArgsConstructor;
import com.demo.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(userService.authenticationUser(loginRequest));
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisrerRequest regisrerRequest) {
        User user = new User();
        user.setUsername(regisrerRequest.getUsername());
        user.setPassword(regisrerRequest.getPassword());
        user.setEmail(regisrerRequest.getEmail());
        user.setRole("ROLE_USER");
        userService.registerUser(user);
        return ResponseEntity.ok("user Register Sucsessfully .. !");
    }
}