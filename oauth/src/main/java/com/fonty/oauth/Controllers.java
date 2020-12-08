package com.fonty.oauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {

    @GetMapping("/all")
    public String all() {
        return "all";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
