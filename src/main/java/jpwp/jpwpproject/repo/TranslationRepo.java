package jpwp.jpwpproject.repo;

import jpwp.jpwpproject.model.Translation;
import jpwp.jpwpproject.util.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepo extends CrudRepository<Translation, Long> {

    @Query(value = "SELECT * FROM Translation WHERE LEVEL = ?2 AND LANGUAGE = ?1 order by RANDOM() LIMIT ?3", nativeQuery = true)
    Iterable<Translation> findByLevelAndLanguage(String language, String level, int amount);

    @Query(value = "SELECT * FROM Translation WHERE LEVEL = ?2 OR LEVEL =?3 AND LANGUAGE = ?1 order by RANDOM() LIMIT ?4", nativeQuery = true)
    Iterable<Translation> findByLanguageAndTwoLevel(String language, String level1, String level2, int amount);

    @Query(value = "SELECT * FROM Translation WHERE LEVEL = ?2 OR LEVEL =?3 OR LEVEL =?4  AND LANGUAGE = ?1 order by RANDOM() LIMIT ?5", nativeQuery = true)
    Iterable<Translation> findByLanguageAndThreeLevel(String language, String level1, String level2, String level3, int amount);

    @Query(value = "SELECT * FROM Translation WHERE LEVEL = ?2 OR LEVEL =?3 OR LEVEL =?4   OR LEVEL =?5   AND LANGUAGE = ?1 order by RANDOM() LIMIT ?6", nativeQuery = true)
    Iterable<Translation> findByLanguageAndFourLevel(String language, String level1, String level2, String level3, String level4, int amount);

    @Query(value = "SELECT * FROM Translation WHERE LEVEL = ?2 OR LEVEL =?3 OR LEVEL =?4 OR LEVEL =?5 OR LEVEL =?6 AND LANGUAGE = ?1 order by RANDOM() LIMIT ?7", nativeQuery = true)
    Iterable<Translation> findByLanguageAndFiveLevel(String language, String level1, String level2, String level3, String level4, String level5, int amount);

    @Query(value = "SELECT * FROM Translation WHERE LEVEL = ?2 OR LEVEL =?3 OR LEVEL =?4 OR LEVEL =?5 OR LEVEL =?6 OR LEVEL =?7 AND LANGUAGE = ?1 order by RANDOM() LIMIT ?8", nativeQuery = true)
    Iterable<Translation> findByLanguageAndSixLevel(String language, String level1, String level2, String level3, String level4, String level5, String level6, int amount);

    @Query(value = "SELECT * FROM Translation WHERE LANGUAGE = ?1  order by RANDOM() LIMIT ?2", nativeQuery = true)
    Iterable<Translation> findByLanguage(String language, int amount);

    Iterable<Translation> findAllByVocabularyStartsWithAndLanguage(String searchFor, Language language);

    Optional<Translation> findByVocabularyAndLanguage(String vocabulary, Language language);
}
