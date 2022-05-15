package jpwp.jpwpproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class KnownTranslations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int EnPointsA1;

    private int EnPointsA2;

    private int EnPointsB1;

    private int EnPointsB2;

    private int EnPointsC1;

    private int EnPointsC2;

    @ElementCollection
    private Set<String> knownVocabulary;

    public KnownTranslations() {
        EnPointsA1 = 0;
        EnPointsA2 = 0;
        EnPointsB1 = 0;
        EnPointsB2 = 0;
        EnPointsC1 = 0;
        EnPointsC2 = 0;
        knownVocabulary = new HashSet<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEnPointsA1() {
        return EnPointsA1;
    }

    public void setEnPointsA1(int enPointsA1) {
        EnPointsA1 = enPointsA1;
    }

    public int getEnPointsA2() {
        return EnPointsA2;
    }

    public void setEnPointsA2(int enPointsA2) {
        EnPointsA2 = enPointsA2;
    }

    public int getEnPointsB1() {
        return EnPointsB1;
    }

    public void setEnPointsB1(int enPointsB1) {
        EnPointsB1 = enPointsB1;
    }

    public int getEnPointsB2() {
        return EnPointsB2;
    }

    public void setEnPointsB2(int enPointsB2) {
        EnPointsB2 = enPointsB2;
    }

    public int getEnPointsC1() {
        return EnPointsC1;
    }

    public void setEnPointsC1(int enPointsC1) {
        EnPointsC1 = enPointsC1;
    }

    public int getEnPointsC2() {
        return EnPointsC2;
    }

    public void setEnPointsC2(int enPointsC2) {
        EnPointsC2 = enPointsC2;
    }

    public Set<String> getKnownVocabulary() {
        return knownVocabulary;
    }

    public void setKnownVocabulary(Set<String> knownVocabulary) {
        this.knownVocabulary = knownVocabulary;
    }
}
