package com.example.testingsystem.services;


import com.example.testingsystem.repositories.UserRepository;
import com.example.testingsystem.users.Roles;
import com.example.testingsystem.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public boolean registrationUser(User user, Roles role) {
        List<Roles> roles = new ArrayList<>();
        roles.add(role);
        user.setUserRoles(roles);
        if (userRepository.existsByName(user.getName())) {
            return false;
        } else {
            System.out.println(user);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}