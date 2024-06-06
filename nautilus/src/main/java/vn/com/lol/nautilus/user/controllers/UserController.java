package vn.com.lol.nautilus.user.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasAuthority('SCOPE_create:file')")
    public String hello() {
        return "Hello";
    }
}
