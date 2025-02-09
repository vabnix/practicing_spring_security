package com.vaibhav.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//By adding EnableWebSecurity we are saying that we will be adding our own custom filter instead of Spring Security Default
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() );
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());

        // to go with stateless session policy
        httpSecurity.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService  userDetailsService(){
//        UserDetails user_1 = User.withDefaultPasswordEncoder()
//                                .username("aum")
//                                .password("aum123")
//                                .roles("USER")
//                                .build();
//
//        UserDetails user_2 = User.withDefaultPasswordEncoder()
//                            .username("ishaan")
//                            .password("ishaan123")
//                            .roles("ADMIN")
//                            .build();
//
//
//        return new InMemoryUserDetailsManager(user_1, user_2);
//    }
}
