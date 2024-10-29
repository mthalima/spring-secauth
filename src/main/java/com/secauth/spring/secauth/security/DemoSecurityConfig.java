package com.secauth.spring.secauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails zallera = User.builder()
                .username("zallera")
                .password("{noop}potatoes")
                .roles("FUNCIONARIO", "GERENTE", "ADM")
                .build();

        UserDetails maria = User.builder()
                .username("maria")
                .password("{noop}22")
                .roles("FUNCIONARIO", "GERENTE")
                .build();

        UserDetails pedro = User.builder()
                .username("pedro")
                .password("{noop}22")
                .roles("FUNCIONARIO")
                .build();

        UserDetails luca = User.builder()
                .username("luca")
                .password("{noop}22")
                .roles("FUNCIONARIO")
                .build();

        UserDetails bilina = User.builder()
                .username("bilina")
                .password("{noop}22")
                .roles("FUNCIONARIO", "GERENTE")
                .build();

        UserDetails osmund = User.builder()
                .username("osmund")
                .password("{noop}22")
                .roles("FUNCIONARIO")
                .build();

        return new InMemoryUserDetailsManager(zallera, maria, pedro, osmund, bilina, luca);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("FUNCIONARIO")
                        .requestMatchers("/gerentes/**").hasRole("GERENTE")
                        .requestMatchers("/systems/**").hasRole("ADM")
                        .anyRequest().authenticated()
                ).formLogin(form->
                    form.loginPage("/myLoginPage")
                            .loginProcessingUrl("/authenticateTheUser")
                            .permitAll()
                ).logout(LogoutConfigurer::permitAll).exceptionHandling(configurer -> configurer.accessDeniedPage("/acesso-negado"));

        return http.build();
    }
}
