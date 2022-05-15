package jpwp.jpwpproject.model;

import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;

import javax.persistence.*;

@Entity
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String vocabulary;

    private String grammaticalCategory;

    private String definition;

    private String collocations;

    private String topic;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    public Translation(Language language, String vocabulary, String grammaticalCategory, String definition, String collocations, String topic, Level level, Word word) {
        this.language = language;
        this.vocabulary = vocabulary;
        this.grammaticalCategory = grammaticalCategory;
        this.definition = definition;
        this.collocations = collocations;
        this.topic = topic;
        this.level = level;
        this.word = word;
    }

    public Translation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
