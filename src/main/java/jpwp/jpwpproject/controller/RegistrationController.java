package jpwp.jpwpproject.controller;

import jpwp.jpwpproject.DTOs.SimpleResponse;
import jpwp.jpwpproject.service.RegistrationService;
import jpwp.jpwpproject.DTOs.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResponse> register(@RequestBody RegistrationRequest request) {
        Long userID = registrationService.register(request);
        return new ResponseEntity<>(new SimpleResponse(userID.toString()), HttpStatus.CREATED);
    }

    @GetMapping(path = "confirm")
    @ResponseBody
    public ModelAndView confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accountConfirmed");
        return modelAndView;
    }
}
