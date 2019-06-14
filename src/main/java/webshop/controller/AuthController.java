package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webshop.entities.AppUser;
import webshop.services.AuthService;

@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/api/auth/register", method = RequestMethod.POST)
    public void registerUser(@RequestBody AppUser userData) {
        authService.registerUser(userData);
    }

    @RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
    public void logInUser(@RequestBody AppUser userData) {
        authService.logInUser(userData);
    }
}
