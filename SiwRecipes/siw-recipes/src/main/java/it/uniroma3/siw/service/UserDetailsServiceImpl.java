package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credenziali;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CredenzialiService credenzialiService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Credenziali cred = credenzialiService.getCredenziali(email);

        if (cred == null) {
            throw new UsernameNotFoundException("Credenziali non trovate per email: " + email);
        }

        return cred; // ora Credenziali implementa UserDetails
    }
}
