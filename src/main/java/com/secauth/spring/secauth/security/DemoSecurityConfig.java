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

        UserDetails joao = User.builder()
                .username("zallera")
                .password("{noop}321654")
                .roles("Funcionario")
                .build();

        UserDetails maria = User.builder()
                .username("maria")
                .password("{noop}test123")
                .roles("Funcionario, Gerente")
                .build();

        UserDetails pedro = User.builder()
                .username("pedro")
                .password("{noop}test123")
                .roles("Funcionario")
                .build();
        return new InMemoryUserDetailsManager(joao, maria, pedro);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer.anyRequest().authenticated()
                ).formLogin(form->
                    form.loginPage("/myLoginPage")
                            .loginProcessingUrl("/authenticateTheUser")
                            .permitAll()
                ).logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
