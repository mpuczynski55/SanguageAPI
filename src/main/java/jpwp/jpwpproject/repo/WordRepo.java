package jpwp.jpwpproject.repo;

import jpwp.jpwpproject.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepo extends CrudRepository<Word, Long> {
    Iterable<Word> findByGrammaticalCategory(String grammaticalCategory);

    Optional<Word> findByVocabulary(String vocabulary);

    boolean existsByVocabulary(String vocabulary);

    Iterable<Word> findAllByVocabularyStartsWith(String searchFor);
}
