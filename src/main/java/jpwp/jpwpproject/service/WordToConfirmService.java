package jpwp.jpwpproject.service;

import jpwp.jpwpproject.DTOs.WordToConfirmDto;
import jpwp.jpwpproject.model.Translation;
import jpwp.jpwpproject.model.Word;
import jpwp.jpwpproject.model.WordToConfirm;
import jpwp.jpwpproject.repo.TranslationRepo;
import jpwp.jpwpproject.repo.WordRepo;
import jpwp.jpwpproject.repo.WordToConfirmRepo;
import jpwp.jpwpproject.util.Language;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WordToConfirmService {
    private WordToConfirmRepo wordToConfirmRepo;
    private WordRepo wordRepo;
    private TranslationRepo translationRepo;

    public WordToConfirmService(WordToConfirmRepo wordToConfirmRepo, WordRepo wordRepo, TranslationRepo translationRepo) {
        this.wordToConfirmRepo = wordToConfirmRepo;
        this.wordRepo = wordRepo;
        this.translationRepo = translationRepo;
    }

    public WordToConfirm save(WordToConfirmDto wordToConfirmDto) {
        String vocabulary = wordToConfirmDto.getVocabulary();
        String grammaticalCategory = wordToConfirmDto.getGrammaticalCategory();
        String collocations = wordToConfirmDto.getCollocations();
        String topic = wordToConfirmDto.getTopic();
        String vocabularyTranslated = wordToConfirmDto.getVocabularyTranslated();
        String grammaticalCategoryTranslated = wordToConfirmDto.getGrammaticalCategoryTranslated();
        String definitionTranslated = wordToConfirmDto.getDefinitionTranslated();
        String collocationsTranslated = wordToConfirmDto.getCollocationsTranslated();
        String topicTranslated = wordToConfirmDto.getTopicTranslated();
        Language language = wordToConfirmDto.getLanguage();
        return wordToConfirmRepo.save(new WordToConfirm(vocabulary, grammaticalCategory, collocations, topic, vocabularyTranslated, grammaticalCategoryTranslated, definitionTranslated,
                collocationsTranslated, topicTranslated, language, LocalDateTime.now().plusMonths(2)));
    }

    public Iterable<WordToConfirm> findAll() {
        return wordToConfirmRepo.findAll();
    }

    @Scheduled(cron = " 0 0 3 * * ?")
    public void saveConfirmed() {
        Iterable<WordToConfirm> allWords = findAll();
        for (WordToConfirm wordToConfirm : allWords) {
            if (wordToConfirm.isEnabled()) {
                String vocabulary = wordToConfirm.getVocabulary();
                Optional<Word> wordRepoByVocabulary = wordRepo.findByVocabulary(vocabulary);
                if (wordRepoByVocabulary.isPresent()) {
                    // polish word already exists
                    Word word = wordRepoByVocabulary.get();
                    String topic = word.getTopic();
                    String grammaticalCategory = word.getGrammaticalCategory();
                    if (wordToConfirm.getTopic().equalsIgnoreCase(topic) && wordToConfirm.getGrammaticalCategory().equalsIgnoreCase(grammaticalCategory)) {
                        Collection<Translation> translation = word.getTranslation();
                        translation.add(new Translation(wordToConfirm.getLanguage(), wordToConfirm.getVocabularyTranslated(), wordToConfirm.getGrammaticalCategoryTranslated(),
                                wordToConfirm.getDefinitionTranslated(), wordToConfirm.getCollocationsTranslated(), wordToConfirm.getTopicTranslated(), wordToConfirm.getLevel(), word));
                        word.setTranslation(translation);
                        wordRepo.save(word);
                    }
                } else {
                    // polish word does not exist
                    Word word = new Word(vocabulary, wordToConfirm.getGrammaticalCategory(), wordToConfirm.getCollocations(), wordToConfirm.getTopic());
                    Translation translation = new Translation(wordToConfirm.getLanguage(), wordToConfirm.getVocabularyTranslated(), wordToConfirm.getGrammaticalCategoryTranslated(), wordToConfirm.getDefinitionTranslated(),
                            wordToConfirm.getCollocationsTranslated(), wordToConfirm.getTopicTranslated(), wordToConfirm.getLevel(), word);
                    List<Translation> translations = Arrays.asList(translation);
                    word.setTranslation(translations);
                    wordRepo.save(word);
                    translationRepo.save(translation);
                }
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void deleteExpired() {
        Iterable<WordToConfirm> allWords = findAll();
        LocalDateTime timeNow = LocalDateTime.now();
        for (WordToConfirm wordToConfirm : allWords) {
            if (wordToConfirm.getExpiresAt().isBefore(timeNow)) {
                wordToConfirmRepo.delete(wordToConfirm);
            }
        }
    }
}
