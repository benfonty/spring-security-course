package com.example.couponservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(final String email, final String password) {
        if (securityService.login(email, password)) {
            return "index";
        }
        else {
            return "login";
        }
    }

    @GetMapping("/showRegisterUser")
    public String showRegisterUser() {
        return "registerUser";
    }

    @PostMapping("/register")
    public String registerUser(final User user) {
        userRepository.save(user.withPassword(passwordEncoder.encode(user.getPassword())));
        return "login";
    }
}
