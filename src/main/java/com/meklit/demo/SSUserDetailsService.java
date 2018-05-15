package com.meklit.demo;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService
{


    private UserRepository userRepository;


    public SSUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
    try {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            return null;
        }
        return new User(
                appUser.getUsername(),
                encoder.encode(appUser.getPassword()),
                getAuthorities(appUser));
    } catch (Exception e){
                throw new UsernameNotFoundException("User not found");
        }
    }
    private Set<GrantedAuthority> getAuthorities(AppUser appUser){
        Set<GrantedAuthority> authorities
                        = new HashSet<GrantedAuthority>();
        for(AppRole role : appUser.getAppRoles()){
            GrantedAuthority grantedAuthority
                            = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
        }

}
