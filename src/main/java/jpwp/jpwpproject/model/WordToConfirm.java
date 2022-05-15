package jpwp.jpwpproject.model;

import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class WordToConfirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vocabulary;

    private String collocations;

    private String grammaticalCategory;

    private String topic;

    private String vocabularyTranslated;

    private String grammaticalCategoryTranslated;

    private String definitionTranslated;

    private String collocationsTranslated;

    private String topicTranslated;

    private boolean enabled;

    private Language language;

    private Level level;

    private LocalDateTime expiresAt;

    public WordToConfirm() {
    }

    public WordToConfirm(String vocabulary, String collocations, String grammaticalCategory, String topic, String vocabularyTranslated, String grammaticalCategoryTranslated, String definitionTranslated, String collocationsTranslated, String topicTranslated, Language language, LocalDateTime expiresAt) {
        this.vocabulary = vocabulary;
        this.collocations = collocations;
        this.grammaticalCategory = grammaticalCategory;
        this.topic = topic;
        this.vocabularyTranslated = vocabularyTranslated;
        this.grammaticalCategoryTranslated = grammaticalCategoryTranslated;
        this.definitionTranslated = definitionTranslated;
        this.collocationsTranslated = collocationsTranslated;
        this.topicTranslated = topicTranslated;
        this.enabled = false;
        this.language = language;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getCollocations() {
        return collocations;
    }

    public void setCollocations(String collocations) {
        this.collocations = collocations;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getVocabularyTranslated() {
        return vocabularyTranslated;
    }

    public void setVocabularyTranslated(String vocabularyTranslated) {
        this.vocabularyTranslated = vocabularyTranslated;
    }

    public String getGrammaticalCategoryTranslated() {
        return grammaticalCategoryTranslated;
    }

    public void setGrammaticalCategoryTranslated(String grammaticalCategoryTranslated) {
        this.grammaticalCategoryTranslated = grammaticalCategoryTranslated;
    }

    public String getDefinitionTranslated() {
        return definitionTranslated;
    }

    public void setDefinitionTranslated(String definitionTranslated) {
        this.definitionTranslated = definitionTranslated;
    }

    public String getCollocationsTranslated() {
        return collocationsTranslated;
    }

    public void setCollocationsTranslated(String collocationsTranslated) {
        this.collocationsTranslated = collocationsTranslated;
    }

    public String getTopicTranslated() {
        return topicTranslated;
    }

    public void setTopicTranslated(String topicTranslated) {
        this.topicTranslated = topicTranslated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getGrammaticalCategory() {
        return grammaticalCategory;
    }

    public void setGrammaticalCategory(String grammaticalCategory) {
        this.grammaticalCategory = grammaticalCategory;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
