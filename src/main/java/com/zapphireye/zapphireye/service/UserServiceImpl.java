package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.User;
import com.zapphireye.zapphireye.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedUser = userRepository.findByUsername(username);
        if(foundedUser == null)
            return null;
        String name = foundedUser.getUsername();
        String pass = foundedUser.getPassword();
        return new org.springframework.security.core.userdetails.User(name, pass, new ArrayList<>());
    }

    public User loadUserRole(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean checkUserByUsername(User user) throws IOException {
        User foundedUser = userRepository.findByUsername(user.getUsername());
        if(foundedUser != null)
            throw new IOException("error message");
        System.out.println(user.getRole());
        System.out.println(user.getUsername());
        userRepository.save(user);
        return true;
    }
}
