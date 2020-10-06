package com.stocksystem.stockmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*@EnableWebMvc*/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    // ...

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/users/create").setViewName("user/register");
        registry.addViewController("/customers/create").setViewName("customer/create");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}