package com.meklit.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data....");

    roleRepository.save(new AppRole("USER"));
    roleRepository.save(new AppRole("ADMIN"));

        AppRole adminRole = roleRepository.findByRole("ADMIN");
        AppRole userRole = roleRepository.findByRole("USER");

        AppUser appUser = new
                AppUser("bob@bob.com", "bob", "Bob", "Bobberson", true, "bob");
        appUser.setAppRoles(Arrays.asList(userRole));
        userRepository.save(appUser);


        appUser = new
                AppUser("jim@jim", "jim", "Jim", "Jimmerson", true, "jim");
        appUser.setAppRoles(Arrays.asList(userRole));
        userRepository.save(appUser);


        appUser = new
                AppUser("admin@admin.com", "admin", "Admin", "User", true, "admin");
        appUser.setAppRoles(Arrays.asList(userRole));
        userRepository.save(appUser);


        appUser = new
                AppUser("sam@ev.com", "pass", "Sam", "Everyman", true, "sam");
        appUser.setAppRoles(Arrays.asList(userRole));
        userRepository.save(appUser);

    }
}
