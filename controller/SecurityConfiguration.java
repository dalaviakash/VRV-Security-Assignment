package org.jspider.securityapi.controller;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //Authentication
    // saved username and password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder user=User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser((user.username("akash").password("123").roles("ADMIN")))
                .withUser((user.username("aniket").password("456").roles("MANAGER")))
                 .withUser((user.username("suraj").password("789").roles("EMPLOYEE")));


    }

    //authorization
    // Based on those role given th aceess

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin").hasRole("ADMIN")   //ONLY ADMIN CAN ACEESS
                .antMatchers("manager").hasAnyRole("MANAGER","ADMIN")  //MANAGER AND ADMIN ALSO CAN ACEESS
                .antMatchers("/employee").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")  // EMPLOYEE ,MAMAGER,ADMIN CAN ACEESS
                //.antMatchers("/").permitAll()
                .and().formLogin();
    }
}
