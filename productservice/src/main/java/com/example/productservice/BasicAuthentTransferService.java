package com.example.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.MessageFormat;
import java.util.Base64;

@Service
public class BasicAuthentTransferService {

    public MultiValueMap<String, String> buildBAsicAuthentHeadersFromSecurityContext() {
        final MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        final User user = (User)(auth.getPrincipal());
        final String email = user.getUsername();
        final String password = auth.getCredentials().toString();
        result.add(
                "Authorization",
                "Basic " + new String(
                        Base64.getEncoder().encode(
                                MessageFormat.format("{0}:{1}", email, password).getBytes())));
        return result;
    }
}
