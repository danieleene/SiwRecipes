package it.uniroma3.siw.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.service.CredenzialiService;

@Component
public class SecurityUtils {

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && !(auth instanceof AnonymousAuthenticationToken);
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }

    public Credenziali getCurrentCredentials(CredenzialiService credenzialiService) {
        String username = getCurrentUsername();
        return username != null ? credenzialiService.getCredenziali(username) : null;
    }
    
    public boolean isAdmin(CredenzialiService credenzialiService) {
    	Credenziali credenziali = getCurrentCredentials(credenzialiService);
    	return credenziali != null && credenziali.getRuolo().equals("ADMIN");
    }
}
