package jpwp.jpwpproject.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED, reason = "user locked")
public class UserLockedException extends RuntimeException {
    public UserLockedException(String message) {
        super(message);
    }
}
