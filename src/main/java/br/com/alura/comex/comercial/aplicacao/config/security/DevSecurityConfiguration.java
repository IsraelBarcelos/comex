package br.com.alura.comex.comercial.aplicacao.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class DevSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
        http.cors().disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs/**", "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**", "/configuration/**", "/swagger-ui", "/spring-security-rest/api/v2/api-docs",
                "/comex/api/swagger-ui/**", "/api/swagger-ui/**", "/swagger-ui/**");
    }

}