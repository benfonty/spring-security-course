package com.fonty.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(AuthorizationServerConfig.MYRESOURCE);
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/all").hasAnyRole("ADMIN","USER")
                .mvcMatchers("/admin").hasAnyRole("ADMIN")
                .anyRequest().denyAll().and().csrf().disable();
    }
}
