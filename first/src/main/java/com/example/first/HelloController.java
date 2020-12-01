package com.example.first;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return "Spring Security rocks";
    }

    @GetMapping("bye")
    public String bye() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return "bye";
    }
}
