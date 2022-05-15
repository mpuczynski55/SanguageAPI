package jpwp.jpwpproject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jpwp.jpwpproject.util.Language;

public class WordToConfirmDto extends WordAndTranslation {

    @JsonFormat
    private boolean enabled;

    public WordToConfirmDto(String vocabulary, String grammaticalCategory, String collocations,
                            String vocabularyTranslated, String grammaticalCategoryTranslated, String definitionTranslated,
                            String collocationsTranslated, Language language, boolean enabled) {
        super(vocabulary, grammaticalCategory, collocations, vocabularyTranslated,
                grammaticalCategoryTranslated, definitionTranslated, collocationsTranslated, language);
        this.enabled = enabled;
    }
}
