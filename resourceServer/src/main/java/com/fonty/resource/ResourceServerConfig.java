package com.fonty.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${oauth.public-key}")
    private String publicKey;

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("myresource");
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and().cors(httpSecurityCorsConfigurer -> {
            CorsConfigurationSource corsConfigurationSource = httpServletRequest -> {
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedMethod("GET");
                config.addAllowedMethod("POST");
                config.addAllowedOrigin("*");
                return config;
            };
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource);
        });
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter result = new JwtAccessTokenConverter();
        result.setVerifierKey(publicKey);
        return result;
    }
}
