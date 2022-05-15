package jpwp.jpwpproject.controller;


import jpwp.jpwpproject.DTOs.UserLoginResponse;
import jpwp.jpwpproject.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginResponse> logIn(@RequestParam String usernameEmail, @RequestParam String password) {
        UserLoginResponse userLoginResponse = loginService.logIn(usernameEmail, password);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }
}
