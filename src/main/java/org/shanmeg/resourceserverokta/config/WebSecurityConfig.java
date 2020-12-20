package org.shanmeg.resourceserverokta.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // To disable security
//        http.authorizeRequests(authorizeRequests ->
//                        authorizeRequests.antMatchers("/**").permitAll()
//                                .anyRequest().authenticated())
//                .csrf().disable();

        // To enable JWT authorization
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .oauth2ResourceServer().jwt();
    }
}
