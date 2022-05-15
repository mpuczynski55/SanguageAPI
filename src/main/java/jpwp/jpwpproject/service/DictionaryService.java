package jpwp.jpwpproject.service;

import jpwp.jpwpproject.DTOs.WordAndTranslation;
import jpwp.jpwpproject.exception.exceptions.NotEnoughWordsException;
import jpwp.jpwpproject.exception.exceptions.WordNotFoundException;
import jpwp.jpwpproject.model.Translation;
import jpwp.jpwpproject.model.Word;
import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DictionaryService {
    private WordService wordService;
    private TranslationService translationService;
    private KnownTranslationsService knownTranslationsService;
    private UserService userService;
    private WordToConfirmService wordToConfirmService;
    private static final int queries_amount_no_user = 15;
    private static final int queries_amount_user = 40;
    private static final int translations_min = 15;

    public DictionaryService(WordService wordService, TranslationService translationService, KnownTranslationsService knownTranslationsService, UserService userService, WordToConfirmService wordToConfirmService) {
        this.wordService = wordService;
        this.translationService = translationService;
        this.knownTranslationsService = knownTranslationsService;
        this.userService = userService;
        this.wordToConfirmService = wordToConfirmService;
    }

    public WordAndTranslation getByLanguageAndVocabulary(Language language, String vocabulary) {
        Optional<Translation> translation = translationService.getTranslationByLanguageVocabulary(language, vocabulary);
        if (!translation.isPresent()) {
            Word word = wordService.findByVocabulary(vocabulary).orElseThrow(() -> new WordNotFoundException("word not found"));
            Translation next = word.getTranslation().iterator().next();
            return createWordAndTranslation(language, next);
        } else {
            return createWordAndTranslation(language, translation.get());
        }
    }

    public HashSet<WordAndTranslation> getMixedByLanguage(Language language) {
        Iterable<Translation> translationsByLanguage = translationService.getTranslationByLanguage(language, queries_amount_no_user);
        return createWordAndTranslation(language, translationsByLanguage);
    }

    public HashSet<WordAndTranslation> getKnownByLanguage(Language language, Long userID) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> translations = new HashSet<>();
        for (String vocabulary : userKnownVocabulary) {
            Translation translation = translationService.getTranslationByLanguageVocabulary(language, vocabulary).orElseThrow(() -> new WordNotFoundException("word not found"));
            translations.add(translation);
        }
        return createWordAndTranslation(language, translations);
    }

    public HashSet<WordAndTranslation> getNewByLanguage(Language language, Long userID) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> newTranslations = new HashSet<>();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguage(language, queries_amount_user);
            HashSet<Translation> filteredTranslationsByKnownVocabulary = filterKnownTranslations(translationByLanguageLevel, userKnownVocabulary);
            newTranslations.addAll(filteredTranslationsByKnownVocabulary);
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public TreeSet<String> getVocabularyStartingWith(Language language, String searchFor) {
        Iterable<Translation> vocabularyStartingWithTranslated = translationService.getVocabularyStartingWith(language, searchFor);
        Iterable<Word> vocabularyStartingWith = wordService.getVocabularyStartingWith(searchFor);
        return createResponseVocabStartingWith(vocabularyStartingWith, vocabularyStartingWithTranslated);
    }


    public HashSet<Translation> filterKnownTranslations(Iterable<Translation> translations, Set<String> knownVocabulary) {
        HashSet<Translation> filteredTranslations = new HashSet<>();
        for (Translation translation : translations) {
            if (!knownVocabulary.contains(translation.getVocabulary())) {
                filteredTranslations.add(translation);
            }
        }
        return filteredTranslations;
    }

    public WordAndTranslation createWordAndTranslation(Language language, Translation translation) {
        Word word = translation.getWord();
        String vocabulary = word.getVocabulary();
        String grammaticalCategory = word.getGrammaticalCategory();
        String collocations = word.getCollocations();
        String topic = word.getTopic();
        String vocabularyTranslated = translation.getVocabulary();
        String grammaticalCategoryTranslated = translation.getGrammaticalCategory();
        String definitionTranslated = translation.getDefinition();
        String collocationsTranslated = translation.getCollocations();
        String topicTranslated = translation.getTopic();
        Level level = translation.getLevel();
        return new WordAndTranslation(vocabulary, grammaticalCategory, collocations, topic, vocabularyTranslated, grammaticalCategoryTranslated,
                definitionTranslated, collocationsTranslated, topicTranslated, language, level);
    }


    public HashSet<WordAndTranslation> createWordAndTranslation(Language language, Iterable<Translation> translations) {
        HashSet<WordAndTranslation> translationHashSet = new HashSet<>();
        for (Translation translation : translations) {
            Word word = translation.getWord();
            String vocabulary = word.getVocabulary();
            String grammaticalCategory = word.getGrammaticalCategory();
            String collocations = word.getCollocations();
            String topic = word.getTopic();
            String vocabularyTranslated = translation.getVocabulary();
            String grammaticalCategoryTranslated = translation.getGrammaticalCategory();
            String definitionTranslated = translation.getDefinition();
            String collocationsTranslated = translation.getCollocations();
            String topicTranslated = translation.getTopic();
            Level level = translation.getLevel();
            translationHashSet.add(new WordAndTranslation(vocabulary, grammaticalCategory, collocations, topic, vocabularyTranslated, grammaticalCategoryTranslated,
                    definitionTranslated, collocationsTranslated, topicTranslated, language, level));
        }
        return translationHashSet;
    }

    public TreeSet<String> createResponseVocabStartingWith(Iterable<Word> words, Iterable<Translation> translations) {
        TreeSet<String> responseHashSet = new TreeSet<>();
        for (Word word : words) {
            responseHashSet.add(word.getVocabulary());
        }
        for (Translation translation : translations) {
            responseHashSet.add(translation.getVocabulary());
        }
        return responseHashSet;
    }

    public HashSet<WordAndTranslation> getNewByLanguageAndLevel(Language language, Long userID, Level level) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> newTranslations = new HashSet<>();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageLevel(language, level, queries_amount_no_user);
            HashSet<Translation> filteredTranslationsByKnownVocabulary = filterKnownTranslations(translationByLanguageLevel, userKnownVocabulary);
            newTranslations.addAll(filteredTranslationsByKnownVocabulary);
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getNewByLanguageAndTwoLevel(Language language, Long userID, Level level1, Level level2) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> newTranslations = new HashSet<>();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageTwoLevel(language, level1, level2, queries_amount_no_user);
            HashSet<Translation> filteredTranslationsByKnownVocabulary = filterKnownTranslations(translationByLanguageLevel, userKnownVocabulary);
            newTranslations.addAll(filteredTranslationsByKnownVocabulary);
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getNewByLanguageAndThreeLevel(Language language, Long userID, Level level1, Level level2, Level level3) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> newTranslations = new HashSet<>();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageThreeLevel(language, level1, level2, level3, queries_amount_no_user);
            HashSet<Translation> filteredTranslationsByKnownVocabulary = filterKnownTranslations(translationByLanguageLevel, userKnownVocabulary);
            newTranslations.addAll(filteredTranslationsByKnownVocabulary);
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getNewByLanguageAndFourLevel(Language language, Long userID, Level level1, Level level2, Level level3, Level level4) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> newTranslations = new HashSet<>();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageFourLevel(language, level1, level2, level3, level4, queries_amount_no_user);
            HashSet<Translation> filteredTranslationsByKnownVocabulary = filterKnownTranslations(translationByLanguageLevel, userKnownVocabulary);
            newTranslations.addAll(filteredTranslationsByKnownVocabulary);
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getNewByLanguageAndFiveLevel(Language language, Long userID, Level level1, Level level2, Level level3, Level level4, Level level5) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        HashSet<Translation> newTranslations = new HashSet<>();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageFiveLevel(language, level1, level2, level3, level4, level5, queries_amount_no_user);
            HashSet<Translation> filteredTranslationsByKnownVocabulary = filterKnownTranslations(translationByLanguageLevel, userKnownVocabulary);
            newTranslations.addAll(filteredTranslationsByKnownVocabulary);
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getKnownByLanguageAndLevel(Language language, Long userID, Level level) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        String[] userKnownVocabularyArray = (String[]) userKnownVocabulary.toArray();
        HashSet<Translation> newTranslations = new HashSet<>();
        int size = userKnownVocabulary.size();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            int random = new Random().nextInt(size);
            String vocab = userKnownVocabularyArray[random];
            Optional<Translation> translationByLanguageVocabulary = translationService.getTranslationByLanguageVocabulary(language, vocab);
            if (translationByLanguageVocabulary.isPresent()) {
                Translation translation = translationByLanguageVocabulary.get();
                if (translation.getLevel().equals(level)) {
                    newTranslations.add(translation);
                }
            }
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getKnownByLanguageAndTwoLevel(Language language, Long userID, Level level1, Level level2) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        String[] userKnownVocabularyArray = (String[]) userKnownVocabulary.toArray();
        HashSet<Translation> newTranslations = new HashSet<>();
        int size = userKnownVocabulary.size();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            int random = new Random().nextInt(size);
            String vocab = userKnownVocabularyArray[random];
            Optional<Translation> translationByLanguageVocabulary = translationService.getTranslationByLanguageVocabulary(language, vocab);
            if (translationByLanguageVocabulary.isPresent()) {
                Translation translation = translationByLanguageVocabulary.get();
                Level level = translation.getLevel();
                if (level.equals(level1) || level.equals(level2)) {
                    newTranslations.add(translation);
                }
            }
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getKnownByLanguageAndThreeLevel(Language language, Long userID, Level level1, Level level2, Level level3) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        String[] userKnownVocabularyArray = (String[]) userKnownVocabulary.toArray();
        HashSet<Translation> newTranslations = new HashSet<>();
        int size = userKnownVocabulary.size();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            int random = new Random().nextInt(size);
            String vocab = userKnownVocabularyArray[random];
            Optional<Translation> translationByLanguageVocabulary = translationService.getTranslationByLanguageVocabulary(language, vocab);
            if (translationByLanguageVocabulary.isPresent()) {
                Translation translation = translationByLanguageVocabulary.get();
                Level level = translation.getLevel();
                if (level.equals(level1) || level.equals(level2) || level.equals(level3)) {
                    newTranslations.add(translation);
                }
            }
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getKnownByLanguageAndFourLevel(Language language, Long userID, Level level1, Level level2, Level level3, Level level4) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        String[] userKnownVocabularyArray = (String[]) userKnownVocabulary.toArray();
        HashSet<Translation> newTranslations = new HashSet<>();
        int size = userKnownVocabulary.size();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            int random = new Random().nextInt(size);
            String vocab = userKnownVocabularyArray[random];
            Optional<Translation> translationByLanguageVocabulary = translationService.getTranslationByLanguageVocabulary(language, vocab);
            if (translationByLanguageVocabulary.isPresent()) {
                Translation translation = translationByLanguageVocabulary.get();
                Level level = translation.getLevel();
                if (level.equals(level1) || level.equals(level2) || level.equals(level3) || level.equals(level4)) {
                    newTranslations.add(translation);
                }
            }
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getKnownByLanguageAndFiveLevel(Language language, Long userID, Level level1, Level level2, Level level3, Level level4, Level level5) {
        HashSet<String> userKnownVocabulary = userService.getUserKnownVocabulary(userID);
        String[] userKnownVocabularyArray = (String[]) userKnownVocabulary.toArray();
        HashSet<Translation> newTranslations = new HashSet<>();
        int size = userKnownVocabulary.size();
        int counter = 0;
        while (newTranslations.size() < translations_min) {
            if (counter == 3) {
                throw new NotEnoughWordsException("not enough words");
            }
            int random = new Random().nextInt(size);
            String vocab = userKnownVocabularyArray[random];
            Optional<Translation> translationByLanguageVocabulary = translationService.getTranslationByLanguageVocabulary(language, vocab);
            if (translationByLanguageVocabulary.isPresent()) {
                Translation translation = translationByLanguageVocabulary.get();
                Level level = translation.getLevel();
                if (level.equals(level1) || level.equals(level2) || level.equals(level3) || level.equals(level4) || level.equals(level5)) {
                    newTranslations.add(translation);
                }
            }
            counter++;
        }
        return createWordAndTranslation(language, newTranslations);
    }

    public HashSet<WordAndTranslation> getMixedByLanguageAndLevel(Language language, Level level1) {
        Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageLevel(language, level1, queries_amount_no_user);
        return createWordAndTranslation(language, translationByLanguageLevel);
    }

    public HashSet<WordAndTranslation> getMixedByLanguageAndTwoLevel(Language language, Level level1, Level level2) {
        Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageTwoLevel(language, level1, level2, queries_amount_no_user);
        return createWordAndTranslation(language, translationByLanguageLevel);
    }

    public HashSet<WordAndTranslation> getMixedByLanguageAndThreeLevel(Language language, Level level1, Level level2, Level level3) {
        Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageThreeLevel(language, level1, level2, level3, queries_amount_no_user);
        return createWordAndTranslation(language, translationByLanguageLevel);
    }

    public HashSet<WordAndTranslation> getMixedByLanguageAndFourLevel(Language language, Level level1, Level level2, Level level3, Level level4) {
        Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageFourLevel(language, level1, level2, level3, level4, queries_amount_no_user);

        return createWordAndTranslation(language, translationByLanguageLevel);
    }

    public HashSet<WordAndTranslation> getMixedByLanguageAndFiveLevel(Language language, Level level1, Level level2, Level level3, Level level4, Level level5) {
        Iterable<Translation> translationByLanguageLevel = translationService.getTranslationByLanguageFiveLevel(language, level1, level2, level3, level4, level5, queries_amount_no_user);

        return createWordAndTranslation(language, translationByLanguageLevel);
    }
}
