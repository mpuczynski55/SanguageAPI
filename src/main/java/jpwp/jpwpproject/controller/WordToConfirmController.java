package jpwp.jpwpproject.controller;

import jpwp.jpwpproject.DTOs.SimpleResponse;
import jpwp.jpwpproject.DTOs.WordToConfirmDto;
import jpwp.jpwpproject.service.WordToConfirmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WordToConfirmController {
    private WordToConfirmService wordToConfirmService;

    public WordToConfirmController(WordToConfirmService wordToConfirmService) {
        this.wordToConfirmService = wordToConfirmService;
    }

    @PostMapping(path = "addWordToConfirm")
    @ResponseBody
    public ResponseEntity<SimpleResponse> addWord(@RequestBody WordToConfirmDto wordToConfirmDto) {
        wordToConfirmService.save(wordToConfirmDto);
        return new ResponseEntity<>(new SimpleResponse("word sent to confirm"), HttpStatus.OK);
    }
}
