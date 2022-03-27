//package ru.edu.cas.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .ignoringAntMatchers("/admin/updateUser/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin").hasAnyRole("Admin")
//                .antMatchers("/admin/**").hasAnyRole("Admin")
//                .antMatchers("/user/**").hasAnyRole("Meneger")
//                .antMatchers("/user/client/**").hasAnyRole("Meneger")
//                .antMatchers("/user/client/create/**").hasAnyRole("Meneger")
//
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
////                .and()
////                .exceptionHandling().accessDeniedPage("/failed.jsp");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
