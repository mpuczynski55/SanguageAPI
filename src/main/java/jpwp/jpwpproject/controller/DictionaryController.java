package jpwp.jpwpproject.controller;

import jpwp.jpwpproject.DTOs.WordAndTranslation;
import jpwp.jpwpproject.service.DictionaryService;
import jpwp.jpwpproject.service.TranslationService;
import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.TreeSet;


@Controller
@RequestMapping(path = "dictionary")
public class DictionaryController {
    private TranslationService translationService;
    private DictionaryService dictionaryService;

    public DictionaryController(TranslationService translationService, DictionaryService dictionaryService) {
        this.translationService = translationService;
        this.dictionaryService = dictionaryService;
    }

    @GetMapping(path = "byLanguageVocabulary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WordAndTranslation> getByLanguageVocabulary(@RequestParam Language language, @RequestParam String vocabulary) {
        return new ResponseEntity<>(dictionaryService.getByLanguageAndVocabulary(language, vocabulary), HttpStatus.OK);
    }

    @GetMapping(path = "mixedByLanguage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getMixedByLanguage(@RequestParam Language language) {
        return new ResponseEntity<>(dictionaryService.getMixedByLanguage(language), HttpStatus.OK);
    }

    @GetMapping(path = "mixedByLanguageLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getMixedByLanguageLevel(@RequestParam Language language, @RequestParam Level level) {
        return new ResponseEntity<>(dictionaryService.getMixedByLanguageAndLevel(language, level), HttpStatus.OK);
    }

    @GetMapping(path = "mixedByLanguageTwoLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getMixedByLanguageLevel(@RequestParam Language language, @RequestParam Level level1, @RequestParam Level level2) {
        return new ResponseEntity<>(dictionaryService.getMixedByLanguageAndTwoLevel(language, level1, level2), HttpStatus.OK);
    }

    @GetMapping(path = "mixedByLanguageThreeLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getMixedByLanguageLevel(@RequestParam Language language, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3) {
        return new ResponseEntity<>(dictionaryService.getMixedByLanguageAndThreeLevel(language, level1, level2, level3), HttpStatus.OK);
    }

    @GetMapping(path = "mixedByLanguageFourLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getMixedByLanguageLevel(@RequestParam Language language, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3, @RequestParam Level level4) {
        return new ResponseEntity<>(dictionaryService.getMixedByLanguageAndFourLevel(language, level1, level2, level3, level4), HttpStatus.OK);
    }

    @GetMapping(path = "mixedByLanguageFiveLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getMixedByLanguageLevel(@RequestParam Language language, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3, @RequestParam Level level4, @RequestParam Level level5) {
        return new ResponseEntity<>(dictionaryService.getMixedByLanguageAndFiveLevel(language, level1, level2, level3, level4, level5), HttpStatus.OK);
    }

    @GetMapping(path = "newByLanguageLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getNewByLanguageLevel(@RequestParam Language language, @RequestParam Level level, @RequestParam Long userID) {
        return new ResponseEntity<>(dictionaryService.getNewByLanguageAndLevel(language, userID, level), HttpStatus.OK);
    }

    @GetMapping(path = "newByLanguageTwoLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getNewByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2) {
        return new ResponseEntity<>(dictionaryService.getNewByLanguageAndTwoLevel(language, userID, level1, level2), HttpStatus.OK);
    }

    @GetMapping(path = "newByLanguageThreeLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getNewByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3) {
        return new ResponseEntity<>(dictionaryService.getNewByLanguageAndThreeLevel(language, userID, level1, level2, level3), HttpStatus.OK);
    }

    @GetMapping(path = "newByLanguageFourLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getNewByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3, @RequestParam Level level4) {
        return new ResponseEntity<>(dictionaryService.getNewByLanguageAndFourLevel(language, userID, level1, level2, level3, level4), HttpStatus.OK);
    }

    @GetMapping(path = "newByLanguageFiveLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getNewByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3, @RequestParam Level level4, @RequestParam Level level5) {
        return new ResponseEntity<>(dictionaryService.getNewByLanguageAndFiveLevel(language, userID, level1, level2, level3, level4, level5), HttpStatus.OK);
    }

    @GetMapping(path = "knownByLanguageLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getKnownByLanguageLevel(@RequestParam Language language, @RequestParam Level level, @RequestParam Long userID) {
        return new ResponseEntity<>(dictionaryService.getKnownByLanguageAndLevel(language, userID, level), HttpStatus.OK);
    }

    @GetMapping(path = "knownByLanguageTwoLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getKnownByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2) {
        return new ResponseEntity<>(dictionaryService.getKnownByLanguageAndTwoLevel(language, userID, level1, level2), HttpStatus.OK);
    }

    @GetMapping(path = "knownByLanguageThreeLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getKnownByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3) {
        return new ResponseEntity<>(dictionaryService.getKnownByLanguageAndThreeLevel(language, userID, level1, level2, level3), HttpStatus.OK);
    }

    @GetMapping(path = "knownByLanguageFourLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getKnownByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3, @RequestParam Level level4) {
        return new ResponseEntity<>(dictionaryService.getKnownByLanguageAndFourLevel(language, userID, level1, level2, level3, level4), HttpStatus.OK);
    }

    @GetMapping(path = "knownByLanguageFiveLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getKnownByLanguageLevel(@RequestParam Language language, @RequestParam Long userID, @RequestParam Level level1, @RequestParam Level level2, @RequestParam Level level3, @RequestParam Level level4, @RequestParam Level level5) {
        return new ResponseEntity<>(dictionaryService.getKnownByLanguageAndFiveLevel(language, userID, level1, level2, level3, level4, level5), HttpStatus.OK);
    }

    @GetMapping(path = "knownByLanguage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getKnownByLanguage(@RequestParam Language language, @RequestParam Long userID) {
        return new ResponseEntity<>(dictionaryService.getKnownByLanguage(language, userID), HttpStatus.OK);
    }

    @GetMapping(path = "newByLanguage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashSet<WordAndTranslation>> getNewWordsByLanguage(@RequestParam Language language, @RequestParam Long userID) {
        return new ResponseEntity<>(dictionaryService.getNewByLanguage(language, userID), HttpStatus.OK);
    }

    @GetMapping(path = "searchSimilarVocabulary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreeSet<String>> getVocabularyStartingWith(@RequestParam Language language, @RequestParam String searchFor) {
        return new ResponseEntity<>(dictionaryService.getVocabularyStartingWith(language, searchFor), HttpStatus.OK);
    }
}
