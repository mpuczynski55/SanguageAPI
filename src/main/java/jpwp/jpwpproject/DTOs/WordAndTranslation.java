package jpwp.jpwpproject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;

public class WordAndTranslation {
    @JsonFormat
    private String vocabulary;
    @JsonFormat
    private String grammaticalCategory;
    @JsonFormat
    private String collocations;
    @JsonFormat
    private String topic;
    @JsonFormat
    private String vocabularyTranslated;
    @JsonFormat
    private String grammaticalCategoryTranslated;
    @JsonFormat
    private String definitionTranslated;
    @JsonFormat
    private String collocationsTranslated;
    @JsonFormat
    private String topicTranslated;
    @JsonFormat
    private Language language;
    @JsonFormat
    private Level level;

    public WordAndTranslation(String vocabulary, String grammaticalCategory, String collocations, String topic, String vocabularyTranslated, String grammaticalCategoryTranslated, String definitionTranslated, String collocationsTranslated, String topicTranslated, Language language, Level level) {
        this.vocabulary = vocabulary;
        this.grammaticalCategory = grammaticalCategory;
        this.collocations = collocations;
        this.topic = topic;
        this.vocabularyTranslated = vocabularyTranslated;
        this.grammaticalCategoryTranslated = grammaticalCategoryTranslated;
        this.definitionTranslated = definitionTranslated;
        this.collocationsTranslated = collocationsTranslated;
        this.topicTranslated = topicTranslated;
        this.language = language;
        this.level = level;
    }

    public WordAndTranslation(String vocabulary, String grammaticalCategory, String collocations, String vocabularyTranslated, String grammaticalCategoryTranslated, String definitionTranslated, String collocationsTranslated, Language language) {
        this.vocabulary = vocabulary;
        this.grammaticalCategory = grammaticalCategory;
        this.collocations = collocations;
        this.vocabularyTranslated = vocabularyTranslated;
        this.grammaticalCategoryTranslated = grammaticalCategoryTranslated;
        this.definitionTranslated = definitionTranslated;
        this.collocationsTranslated = collocationsTranslated;
        this.language = language;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getGrammaticalCategory() {
        return grammaticalCategory;
    }

    public void setGrammaticalCategory(String grammaticalCategory) {
        this.grammaticalCategory = grammaticalCategory;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
