package com.example.first;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        Object passwordObject = authentication.getCredentials();
        String password = passwordObject.toString();
        if ("ben".equals(userName)) {
            return new UsernamePasswordAuthenticationToken(userName, password, Collections.EMPTY_LIST);
        }
        else {
            throw new BadCredentialsException("bad credentials");
        }
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
