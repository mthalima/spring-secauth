package com.secauth.spring.secauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class DemoSecurityConfig {

    // Suporte a JDBC

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //query para retornar um usuario pelo username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        //query para retornar as authorities/roles por username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
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

       /*
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
*/
}
