package com.algohire.backend.service;

import com.algohire.backend.model.Users;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepository.findByUserName(username);

        if(users==null){
            throw new UsernameNotFoundException("no user found");
        }
        return new CustomUserDetails(users);
    }

    public Users chekIfUsersAlredyExist(Users users){
        return userRepository.findByUserName(users.getUsername());
    }

}
