package ru.edu.cas.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

@Component
public class CustomDetailUserService implements UserDetailsService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User info = repository.findByLogin(login);
        if (info == null) {
            throw new UsernameNotFoundException("User not found " + info.getLogin());
        }

        String decodedPassword = new String(Base64.getDecoder().decode(info.getPassword()));
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + info.getRoleId().getRole()));
        return new org.springframework.security.core.userdetails.User(info.getLogin(), decodedPassword, authorities);
    }
}
