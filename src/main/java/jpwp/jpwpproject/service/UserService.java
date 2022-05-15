package jpwp.jpwpproject.service;

import jpwp.jpwpproject.DTOs.SimpleResponse;
import jpwp.jpwpproject.exception.exceptions.IncorrectPasswordException;
import jpwp.jpwpproject.exception.exceptions.UserKnownVocabularyExistsException;
import jpwp.jpwpproject.exception.exceptions.UsernameNotFoundException;
import jpwp.jpwpproject.model.KnownTranslations;
import jpwp.jpwpproject.model.Translation;
import jpwp.jpwpproject.model.User;
import jpwp.jpwpproject.repo.UserRepo;
import jpwp.jpwpproject.util.Language;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService {
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;
    private KnownTranslationsService knownTranslationsService;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService, KnownTranslationsService knownTranslationsService) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.knownTranslationsService = knownTranslationsService;
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public void signUpUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        save(user);
    }

    public void enableUser(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setEnabled(true);
            save(updateUser);
        }
    }

    public void changePassword(Long userID, String oldPassword, String newPassword) {
        User user = findById(userID).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        String oldPasswordEncoded = bCryptPasswordEncoder.encode(oldPassword);
        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            String newPasswordEncoded = bCryptPasswordEncoder.encode(newPassword);
            user.setPassword(newPasswordEncoded);
            save(user);
        } else {
            throw new IncorrectPasswordException("incorrect password");
        }
    }

    public void changeUsername(Long userID, String newUsername, String password) {
        User user = findById(userID).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            user.setUsername(newUsername);
            save(user);
        } else {
            throw new IncorrectPasswordException("incorrect password");
        }
    }

    public Set<Translation> removeKnown(Iterable<Translation> translations, Long userID, Language language) {
        User user = findById(userID).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Set<String> knownTranslations = user.getKnownTranslations().getKnownVocabulary();
        Iterator<Translation> translationIterator = translations.iterator();
        return StreamSupport.stream(translations.spliterator(), false).filter(t -> knownTranslations.contains(t.getVocabulary())).collect(Collectors.toSet());
    }


    public KnownTranslations getUserKnownTranslations(Long userID) {
        User user = findById(userID).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getKnownTranslations();
    }

    public HashSet<String> getUserKnownVocabulary(Long userID) {
        User user = findById(userID).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Set<String> knownVocabulary = user.getKnownTranslations().getKnownVocabulary();
        return new HashSet<>(knownVocabulary);
    }

    public void addUserKnownVocabulary(Long userID, String vocabulary) {
        KnownTranslations userKnownTranslations = getUserKnownTranslations(userID);
        Set<String> userKnownVocabulary = userKnownTranslations.getKnownVocabulary();
        if (userKnownVocabulary.contains(vocabulary)) {
            throw new UserKnownVocabularyExistsException("vocabulary exists");
        }
        userKnownVocabulary.add(vocabulary);

        knownTranslationsService.save(userKnownTranslations);
    }

    public void deleteKnownVocab(Long userID, String vocabulary) {
        KnownTranslations userKnownTranslations = getUserKnownTranslations(userID);
        Set<String> knownVocabulary = userKnownTranslations.getKnownVocabulary();
        knownVocabulary.remove(vocabulary);
        knownTranslationsService.save(userKnownTranslations);
    }

    public SimpleResponse getUserEnabled(Long userID) {
        User user = findById(userID).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        if (user.getEnabled()) {
            return new SimpleResponse("enabled");
        } else {
            return new SimpleResponse("not enabled");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws org.springframework.security.core.userdetails.UsernameNotFoundException {
        return null;
    }
}
