package jpwp.jpwpproject.service;

import jpwp.jpwpproject.DTOs.RegistrationRequest;
import jpwp.jpwpproject.exception.exceptions.*;
import jpwp.jpwpproject.model.ConfirmationToken;
import jpwp.jpwpproject.model.KnownTranslations;
import jpwp.jpwpproject.model.User;
import jpwp.jpwpproject.util.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    public RegistrationService(UserService userService, EmailValidator emailValidator, ConfirmationTokenService confirmationTokenService, EmailService emailService) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }

    public Long register(RegistrationRequest registrationRequest) {
        boolean isValidEmail = emailValidator.test(registrationRequest.getEmail());
        if (!isValidEmail) {
            throw new EmailNotValidException("email not valid");
        }
        String username = registrationRequest.getUsername();
        String email = registrationRequest.getEmail();
        if (userService.existsByEmail(email) || userService.existsByUsername(username)) {
            throw new UserExistsException("user already exists");
        }
        String password = registrationRequest.getPassword();
        LocalDate timeNow = LocalDate.now();
        Language secondLanguage = registrationRequest.getSecondLanguage();
        Set<Language> secondLanguages = new HashSet<>();
        secondLanguages.add(secondLanguage);
        User user = new User(username, password, timeNow, email, secondLanguages, new KnownTranslations());
        userService.signUpUser(user);

        String token = confirmationTokenService.generateToken(user);
        String link = "https://sanguage.herokuapp.com/registration/confirm?token=" + token;
        emailService.send(
                registrationRequest.getEmail(),
                emailService.buildConfirmAccountEmail(link));
        return user.getId();
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .findByToken(token)
                .orElseThrow(() ->
                        new TokenNotFoundException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmedException("email already confirmed");
        } else if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
    }
}

