package jpwp.jpwpproject.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "not enough words")
public class NotEnoughWordsException extends RuntimeException{
    public NotEnoughWordsException(String message) {
        super(message);
    }
}
