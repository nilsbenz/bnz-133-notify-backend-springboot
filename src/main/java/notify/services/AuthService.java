package notify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import notify.authentication.IAuthenticationFacade;
import notify.entities.AppUser;
import notify.repositories.AppUserRepository;

@Service
public class AuthService {

    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private IAuthenticationFacade authenticationFacade;

    @Autowired
    AuthService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, IAuthenticationFacade authenticationFacade) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationFacade = authenticationFacade;
    }

    public void registerUser(AppUser user) {
        for (AppUser u : appUserRepository.findAll()) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new IllegalArgumentException("Dieser Benutzername ist bereits vergeben.");
            }
            if (u.getMail().equalsIgnoreCase(user.getMail())) {
                throw new IllegalArgumentException("Diese Mailadresse ist bereits vergeben.");
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }

    public void logInUser(AppUser user) {
        AppUser userByUsername = appUserRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        if (!bCryptPasswordEncoder.matches(user.getPassword(), userByUsername.getPassword())) {
            throw new IllegalArgumentException("invalid usercredentials");
        }
    }

    public AppUser getCurrentUser() {
        String user = authenticationFacade.getAuthentication().getName();
        return appUserRepository.findByUsername(user);
    }
}
