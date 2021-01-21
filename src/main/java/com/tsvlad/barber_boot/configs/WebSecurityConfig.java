package com.tsvlad.barber_boot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                    .authoritiesByUsernameQuery("SELECT users.username, roles.role_name " +
                            "FROM users JOIN authorities ON users.id = authorities.user_id JOIN roles ON authorities.role_id = roles.id " +
                            "WHERE username=?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/*").hasRole("ADMIN")
                    .antMatchers("/barber/*").hasRole("BARBER")
                    .antMatchers("/login*").permitAll()
                    .and()
                .formLogin()
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .and()
                .rememberMe()
                    .key("barbershopKey")
                    .tokenValiditySeconds(2419200)
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                .csrf()
                    .disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
