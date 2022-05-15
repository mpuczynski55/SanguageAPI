package jpwp.jpwpproject.exception;

import jpwp.jpwpproject.exception.exceptions.TokenExpiredException;
import jpwp.jpwpproject.exception.exceptions.TokenNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    public String handleTokenException(TokenExpiredException ex, Model model) {
       // TODO
        return "token_expired";
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public String handleTokenException(TokenNotFoundException ex, Model model) {
        // TODO
        return "token_not_found";
    }

}
