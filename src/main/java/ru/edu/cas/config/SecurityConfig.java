package ru.edu.cas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .ignoringAntMatchers("/admin/updateUser/**")
//                 .ignoringAntMatchers("/user/info/**")
//                .and()
                .authorizeRequests()
                .antMatchers("/login", "/style.css", "/script.js").permitAll()
                .antMatchers("/admin").hasAnyRole("Admin")
                .antMatchers("/admin/**").hasAnyRole("Admin")
                .antMatchers("/user/**").hasAnyRole("Meneger")
                .antMatchers("/account/**").hasAnyRole("Client")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/");
//                .and()
//                .exceptionHandling().accessDeniedPage("/failed.jsp");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
