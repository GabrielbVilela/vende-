package com.NRG.vende.service;

import com.NRG.vende.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository r;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return r.findByEmail(username);
    }
}
