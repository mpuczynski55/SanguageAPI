package jpwp.jpwpproject.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vocabulary;

    private String grammaticalCategory;

    private String collocations;

    private String topic;

    @OneToMany(mappedBy = "word")
    private Collection<Translation> translation = new java.util.ArrayList<>();

    public Word(String vocabulary, String grammaticalCategory, String collocations, String topic) {
        this.vocabulary = vocabulary;
        this.grammaticalCategory = grammaticalCategory;
        this.collocations = collocations;
        this.topic = topic;
    }

    public Word(String vocabulary, String collocations, String topic) {
        this.vocabulary = vocabulary;
        this.collocations = collocations;
        this.topic = topic;
    }

    public Word() {
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

    public Collection<Translation> getTranslation() {
        return translation;
    }

    public void setTranslation(Collection<Translation> translation) {
        this.translation = translation;
    }
}
