package com.longpc.hanashopspringapp;

import com.cksource.ckfinder.servlet.CKFinderServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
public class HanashopSpringAppApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(HanashopSpringAppApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "PUT", "POST", "OPTIONS");
//                        .allowedHeaders("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Content-Type-Options, X-Requested-With, Content-Type, Accept");
            }
        };
    }
}
