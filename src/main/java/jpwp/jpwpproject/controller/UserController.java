package jpwp.jpwpproject.controller;

import jpwp.jpwpproject.DTOs.SimpleResponse;
import jpwp.jpwpproject.service.KnownTranslationsService;
import jpwp.jpwpproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.TreeSet;

@Controller
@RequestMapping(path = "user")
public class UserController {
    private final UserService userService;
    private final KnownTranslationsService knownTranslationsService;

    public UserController(UserService userService, KnownTranslationsService knownTranslationsService) {
        this.userService = userService;
        this.knownTranslationsService = knownTranslationsService;
    }

    @PostMapping(path = "passChange")
    @ResponseBody
    public ResponseEntity<SimpleResponse> changePassword(@RequestParam Long userID, @RequestParam String oldPassword, @RequestParam String newPassword) {
        userService.changePassword(userID, oldPassword, newPassword);
        return new ResponseEntity<>(new SimpleResponse("password changed successfully"), HttpStatus.OK);
    }

    @PostMapping(path = "usernameChange")
    @ResponseBody
    public ResponseEntity<SimpleResponse> changeUsername(@RequestParam Long userID, @RequestParam String newUsername, @RequestParam String password) {
        userService.changeUsername(userID, newUsername, password);
        return new ResponseEntity<>(new SimpleResponse("username changed successfully"), HttpStatus.OK);
    }

    @PostMapping(path = "addKnownVocab")
    @ResponseBody
    public ResponseEntity<SimpleResponse> addKnownVocabulary(@RequestParam Long userID, @RequestParam String vocabulary) {
        userService.addUserKnownVocabulary(userID, vocabulary);
        return new ResponseEntity<>(new SimpleResponse("added"), HttpStatus.OK);
    }

    @GetMapping(path = "getKnownVocab")
    @ResponseBody
    public ResponseEntity<TreeSet<String>> addKnownVocabulary(@RequestParam Long userID) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        TreeSet<String> userKnownVocabularyOrder = new TreeSet<>(userKnownVocabulary);
        return new ResponseEntity<>(userKnownVocabularyOrder, HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteKnownVocab")
    @ResponseBody
    public ResponseEntity<SimpleResponse> deleteKnownVocab(@RequestParam Long userID, @RequestParam String vocabulary) {
        userService.deleteKnownVocab(userID, vocabulary);
        return new ResponseEntity<>(new SimpleResponse("deleted"), HttpStatus.OK);
    }

    @GetMapping(path = "getUserEnabled")
    @ResponseBody
    public ResponseEntity<SimpleResponse> getUserEnabled(@RequestParam Long userID) {
        return new ResponseEntity<>(userService.getUserEnabled(userID), HttpStatus.OK);
    }
}
