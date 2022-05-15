package jpwp.jpwpproject.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "vocabulary exists")
public class UserKnownVocabularyExistsException extends RuntimeException {
    public UserKnownVocabularyExistsException(String message) {
        super(message);
    }
}
