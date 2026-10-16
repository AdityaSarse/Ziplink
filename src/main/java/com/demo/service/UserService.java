package com.demo.service;

import com.demo.dtos.LoginRequest;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.security.JwtUtils;
import lombok.AllArgsConstructor;
import com.demo.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.repository.UserReository;

import java.security.Security;


@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserReository userReository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userReository.save(user);
    }

    public JwtAuthenticationResponse authenticationUser(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt) ;
    }

}
