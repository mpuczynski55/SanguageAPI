package jpwp.jpwpproject.service;

import jpwp.jpwpproject.model.Word;
import jpwp.jpwpproject.repo.WordRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordService {
    private WordRepo wordRepo;

    public WordService(WordRepo wordRepo) {
        this.wordRepo = wordRepo;
    }

    public Word save(Word word) {
        return wordRepo.save(word);
    }

    public Optional<Word> findByVocabulary(String vocabulary) {
        return wordRepo.findByVocabulary(vocabulary);
    }

    public Iterable<Word> findByCategory(String grammaticalCategory) {
        return wordRepo.findByGrammaticalCategory(grammaticalCategory);
    }

    public boolean existsByVocabulary(String vocabulary) {
        return wordRepo.existsByVocabulary(vocabulary);
    }

    public Iterable<Word> getVocabularyStartingWith(String searchFor) {
        return wordRepo.findAllByVocabularyStartsWith(searchFor);
    }
}
