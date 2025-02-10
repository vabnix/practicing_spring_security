package com.vaibhav.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request)->
                request.requestMatchers("/**").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }


    // In memory user authentication
    @Bean
    UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("user1")
                .password("{noop}password")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        createUserIfNotExists(jdbcUserDetailsManager, "user1", "password", "USER");
        createUserIfNotExists(jdbcUserDetailsManager, "admin", "password", "ADMIN");

        return jdbcUserDetailsManager;
    }

    private void createUserIfNotExists(JdbcUserDetailsManager manager, String username,
                                       String password, String role) {
        if (!manager.userExists(username)) {
            UserDetails user = User.withUsername(username)
                    .password("{noop}" + password)
                    .roles(role)
                    .build();
            manager.createUser(user);
        }
    }
}
