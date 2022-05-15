package jpwp.jpwpproject.service;

import jpwp.jpwpproject.model.Translation;
import jpwp.jpwpproject.repo.TranslationRepo;
import jpwp.jpwpproject.repo.WordRepo;
import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TranslationService {
    private final TranslationRepo translationRepo;
    private final WordRepo wordRepo;
    public final UserService userService;

    public TranslationService(TranslationRepo translationRepo, WordRepo wordRepo, UserService userService) {
        this.translationRepo = translationRepo;
        this.wordRepo = wordRepo;
        this.userService = userService;
    }

    public Translation save(Translation translation) {
        return translationRepo.save(translation);
    }

    public Optional<Translation> getTranslationById(Long id) {
        return translationRepo.findById(id);
    }

    public Optional<Translation> getTranslationByLanguageVocabulary(Language language, String vocabulary) {
        return translationRepo.findByVocabularyAndLanguage(vocabulary, language);
    }

    public Iterable<Translation> getTranslationByLanguageLevel(Language language, Level level, int amount) {
        return translationRepo.findByLevelAndLanguage(language.toString(), level.toString(), amount);
    }

    public Iterable<Translation> getTranslationByLanguageTwoLevel(Language language, Level level1, Level level2, int amount) {
        return translationRepo.findByLanguageAndTwoLevel(language.toString(), level1.toString(), level2.toString(), amount);
    }

    public Iterable<Translation> getTranslationByLanguageThreeLevel(Language language, Level level1, Level level2, Level level3, int amount) {
        return translationRepo.findByLanguageAndThreeLevel(language.toString(), level1.toString(), level2.toString(), level3.toString(), amount);
    }

    public Iterable<Translation> getTranslationByLanguageFourLevel(Language language, Level level1, Level level2, Level level3, Level level4, int amount) {
        return translationRepo.findByLanguageAndFourLevel(language.toString(), level1.toString(), level2.toString(), level3.toString(), level4.toString(), amount);
    }

    public Iterable<Translation> getTranslationByLanguageFiveLevel(Language language, Level level1, Level level2, Level level3, Level level4, Level level5, int amount) {
        return translationRepo.findByLanguageAndFiveLevel(language.toString(), level1.toString(), level2.toString(), level3.toString(), level4.toString(), level5.toString(), amount);
    }

    public Iterable<Translation> getTranslationByLanguageSixLevel(Language language, Level level1, Level level2, Level level3, Level level4, Level level5, Level level6, int amount) {
        return translationRepo.findByLanguageAndSixLevel(language.toString(), level1.toString(), level2.toString(), level3.toString(), level4.toString(), level5.toString(), level6.toString(), amount);
    }

    public Iterable<Translation> getTranslationByLanguage(Language language, int amount) {
        return translationRepo.findByLanguage(language.toString(), amount);
    }

    public Iterable<Translation> getVocabularyStartingWith(Language language, String searchFor) {
        return translationRepo.findAllByVocabularyStartsWithAndLanguage(searchFor, language);
    }

}
