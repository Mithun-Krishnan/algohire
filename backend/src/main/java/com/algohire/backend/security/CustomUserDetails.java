package com.algohire.backend.security;

import com.algohire.backend.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CustomUserDetails implements UserDetails {

    private String userName;
    private String password;
    private UUID id;

    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Users user) {
        this.userName=user.getUsername();
        this.id=user.getId();
        this.password=user.getPassword();

        List<GrantedAuthority> auths=new ArrayList<>();

        if(user.getRole()!=null){
            String roleName=user.getRole().getRole().name();
            auths.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        }
        this.authorities=auths;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
