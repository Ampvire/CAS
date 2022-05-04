package ru.edu.cas.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.edu.cas.clients_account.dao.AccountClient;
import ru.edu.cas.clients_account.repo.AccountClientRepository;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

@Component
public class CustomDetailUserService implements UserDetailsService {
    private UserRepository repository;
    private AccountClientRepository clientRepository;

    @Autowired
    public void setClientRepository(AccountClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User info = repository.findByLogin(login);
        AccountClient client = clientRepository.findByLogin(login);
        if (info == null && client == null) {
            throw new UsernameNotFoundException("User not found " + login);
        }
        String decodedPassword =null;
        String role = null;
        String loginDb = null;
        if (info != null) {
            decodedPassword = new String(Base64.getDecoder().decode(info.getPassword()));
            role = info.getRoleId().getRole();
            loginDb = info.getLogin();
        }
        if (client != null) {
            decodedPassword = new String(Base64.getDecoder().decode(client.getPassword()));
            role = client.getRoleId().getRole();
            loginDb = client.getLogin();
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return new org.springframework.security.core.userdetails.User(loginDb, decodedPassword, authorities);
    }
}
