package com.stocksystem.stockmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
//        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/contactUs").setViewName("contactUs");
        registry.addViewController("/about").setViewName("about");
//        registry.addViewController("/details").setViewName("profile");
        registry.addViewController("/users/create").setViewName("user/register");
        registry.addViewController("/customers/create").setViewName("customer/create");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/productTypes")
//                .allowedOrigins("http://localhost:8888")
//                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//    }

}