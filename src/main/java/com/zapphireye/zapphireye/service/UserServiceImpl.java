package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.User;
import com.zapphireye.zapphireye.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
