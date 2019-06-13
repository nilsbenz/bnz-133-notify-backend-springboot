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

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/api/auth/register", method = RequestMethod.POST)
    public Long registerUser(@RequestBody AppUser userData) {
        return authService.registerUser(userData);
    }

    @RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
    public Long logInUser(@RequestBody AppUser userData) {
        return authService.logInUser(userData);
    }
}
