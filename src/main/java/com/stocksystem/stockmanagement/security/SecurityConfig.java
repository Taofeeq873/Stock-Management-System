package com.stocksystem.stockmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   /* @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }*/

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")

                .antMatchers("/customers/**").hasRole("ADMIN")
                .antMatchers("/products/**").hasRole("ADMIN")
                .antMatchers("/productTypes/**").hasRole("ADMIN")
                .antMatchers("/purchases/**").hasRole("ADMIN")
                .antMatchers("/roles/**").hasRole("ADMIN")
                .antMatchers("/sales/**").hasRole("ADMIN")
                .antMatchers("/suppliers/**").hasRole("ADMIN")
                .antMatchers("/users/list").hasRole("ADMIN")
                .antMatchers("/users/edit").hasRole("ADMIN")
                .antMatchers("/users/assignRole").hasRole("ADMIN")

                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/").permitAll()
                .antMatchers("/contactUs").permitAll()
                .antMatchers("/layout").permitAll()
                .antMatchers("/about").permitAll()

//                .antMatchers("/purchase/list/**").permitAll()
//                .antMatchers("/purchase/**").permitAll()
//                .antMatchers("/purchase").permitAll()
//
//                .antMatchers("/suppliers/**").permitAll()
//                .antMatchers("/suppliers/list/**").permitAll()
//
//                .antMatchers("/productTypes/**").permitAll()
//                .antMatchers("/productTypes/list/**").permitAll()

                .antMatchers("/login*").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/users/register").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/index", true)
                //.failureUrl("/login.html?error=true")
                //.failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                //.logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");
                //.logoutSuccessHandler(logoutSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/icons/**","/plugins/**", "/fonts/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
