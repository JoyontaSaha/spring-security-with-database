package com.joyonta.springsecuritywithdatabase.config;

import com.joyonta.springsecuritywithdatabase.repository.ApiUrlRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ApiUrlRoleRepository apiUrlRoleRepository;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider
                = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Map<String, String> apiUrlAndRoleHashMap = new HashMap<>();

        apiUrlRoleRepository.findAll().forEach(apiUrlRole -> apiUrlAndRoleHashMap.put(apiUrlRole.getApiUrl(), apiUrlRole.getRole()));

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/").permitAll();

        apiUrlAndRoleHashMap.entrySet().forEach(entry -> {
            try {
                http.authorizeRequests().antMatchers(entry.getKey()).hasAuthority(entry.getValue());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

    }
}
