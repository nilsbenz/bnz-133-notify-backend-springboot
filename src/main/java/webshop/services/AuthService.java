package webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webshop.entities.AppUser;
import webshop.repositories.AppUserRepository;

@Service
public class AuthService {

    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Long registerUser(AppUser user) {
        for (AppUser u : appUserRepository.findAll()) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new IllegalArgumentException("username already taken");
            }
            if (u.getMail().equalsIgnoreCase(user.getMail())) {
                throw new IllegalArgumentException("email already taken");
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        return user.getId();
    }

    public Long logInUser(AppUser user) {
        AppUser userByUsername = appUserRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findAny()
                .get();

        if (bCryptPasswordEncoder.matches(user.getPassword(), userByUsername.getPassword())) {
            return appUserRepository.findByUsername(user.getUsername()).getId();
        }
        throw new IllegalArgumentException("invalid usercredentials");
    }
}
