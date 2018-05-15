package com.meklit.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
}

public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
}
public AppUser countByEmail(String email) {
        return userRepository.countByEmail(email);
    }
public AppUser findByUsername(String username){
        return userRepository.findByUsername(username);
}

public void saveUser(AppUser user){
        user.setAppRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
}

public void saveAdmin(AppUser user){
        user.setAppRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
}
}
