package com.zapphireye.zapphireye.model.controller;

import com.zapphireye.zapphireye.model.database.User;
import com.zapphireye.zapphireye.model.request.CreateAuthRequest;
import com.zapphireye.zapphireye.model.response.AuthResponse;
import com.zapphireye.zapphireye.repository.UserRepository;
import com.zapphireye.zapphireye.service.UserServiceImpl;
import com.zapphireye.zapphireye.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value = "/dashboard")
    public String testingToken() {
        return "Welcome to the Dashboard";
    }

    @PostMapping(value = "/subs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> subscribeClient(@RequestBody CreateAuthRequest createAuthRequest) {
        String username = createAuthRequest.getUsername();
        String password = createAuthRequest.getPassword();
        String role = createAuthRequest.getRole();
        System.out.println("role controller " + role);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        try {
            userService.checkUserByUsername(user);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse("Username " + username + " Has Been Taken"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse("Error : " + e));
        }
        return ResponseEntity.ok(new AuthResponse("Succesful Subscription for client " + username));
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody CreateAuthRequest createAuthRequest) {

        String username = createAuthRequest.getUsername();
        String password = createAuthRequest.getPassword();
        String role = userService.loadUserRole(username).getRole();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthResponse("Username " + username + " Not Authorized"));
            return ResponseEntity.ok(new AuthResponse("Error During Authentication for client " + username));
        }
        UserDetails loadedUser = userService.loadUserByUsername(username);
        String generatedToken = jwtUtils.generateToken(loadedUser);
        AuthResponse response = new AuthResponse(generatedToken);
        response.setRole(role);
        return ResponseEntity.ok(response);
    }
}
