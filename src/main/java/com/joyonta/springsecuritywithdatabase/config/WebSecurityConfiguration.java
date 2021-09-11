package com.joyonta.springsecuritywithdatabase.config;

import com.joyonta.springsecuritywithdatabase.repository.ApiUrlRoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

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
        System.out.println("configure() called");

        Map<KeyPair, List<String>> apiUrlAndRoleHashMap = new HashMap<>();

        apiUrlRoleRepository.findAll().forEach(apiUrlRole ->
        {
            KeyPair keyPair = new KeyPair(apiUrlRole.getApiurl(), apiUrlRole.getMethodname());
            List<String> roles = apiUrlAndRoleHashMap.get(keyPair);
            System.out.println("apiUrlAndRoleHashMap.get(keyPair) " + apiUrlAndRoleHashMap.get(keyPair));

            if(roles == null ) {
                roles = new ArrayList<>();
            }
            System.out.println("roles: " + roles);
            roles.add(apiUrlRole.getRole());

            apiUrlAndRoleHashMap.put(keyPair, roles);

            System.out.println("apiUrlAndRoleHashMap: " + apiUrlAndRoleHashMap);

        });

        apiUrlAndRoleHashMap.entrySet().forEach(entry ->
                        System.out.println("key: " + entry.getKey() + " value: " + entry.getValue()));

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/deleteDepartmentById/**").hasRole("ADMIN");

        /*apiUrlAndRoleHashMap.entrySet().forEach(entry -> {
            try {
                String[] roles = entry.getValue().stream().toArray(String[]::new);
                System.out.println("roles: " + Arrays.toString(roles));

                String apiurl = entry.getKey().getApiurl();
                System.out.println("apiurl : " + apiurl);
                if(roles.length > 1) {
//                    http.authorizeRequests().antMatchers(getHttpMethod(entry.getKey().getMethodname()), entry.getKey().getApiurl()).hasAnyAuthority(roles);
                    http.authorizeRequests().antMatchers(apiurl).hasAnyAuthority(roles);

                } else {
//                    http.authorizeRequests().antMatchers(getHttpMethod(entry.getKey().getMethodname()), entry.getKey().getApiurl()).hasAuthority(roles[0]);
                    http.authorizeRequests().antMatchers(apiurl).hasAuthority(roles[0]);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });*/

        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

    }

    private static HttpMethod getHttpMethod(String methodName) {

        System.out.println("getHttpMethod() called");
        System.out.println("methodname : " + methodName);

        switch (methodName) {
            case "POST":
                return HttpMethod.POST;
            case "PUT":
                return HttpMethod.PUT;
            case "DELETE":
                return HttpMethod.DELETE;
            default:
                return HttpMethod.GET;
        }
    }

    @Data
    private class KeyPair {
        private String apiurl;
        private String methodname;

        public KeyPair(String apiurl, String methodname) {
            this.apiurl = apiurl;
            this.methodname = methodname;
        }
    }
}
