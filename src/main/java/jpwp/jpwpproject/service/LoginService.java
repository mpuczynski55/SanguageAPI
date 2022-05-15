package jpwp.jpwpproject.service;

import jpwp.jpwpproject.DTOs.UserLoginResponse;
import jpwp.jpwpproject.exception.exceptions.*;
import jpwp.jpwpproject.model.User;
import jpwp.jpwpproject.util.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(UserService userService, EmailValidator emailValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserLoginResponse logIn(String usernameOrEmail, String password) {
        boolean isValidEmail = emailValidator.test(usernameOrEmail);
        User user;
        if (!isValidEmail) {
            // login with username
            user = userService.findByUsername(usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        } else {
            //login with email
            isValidEmail = emailValidator.test(usernameOrEmail);
            if (!isValidEmail) {
                throw new EmailNotValidException("email not valid");
            }
            user = userService.findByEmail(usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        }
        if (!user.getEnabled()) {
            throw new EmailNotConfirmedException("email not confirmed");
        } else if (user.getLocked()) {
            throw new UserLockedException("user blocked");
        } else if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("incorrect password");
        }
        return new UserLoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRegistrationDate(),
                user.getSecondLanguage());
    }
}
