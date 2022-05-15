package jpwp.jpwpproject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jpwp.jpwpproject.util.Language;

import java.time.LocalDate;
import java.util.Set;

public class UserLoginResponse {
    @JsonFormat
    private Long userID;
    @JsonFormat
    private String username;

    @JsonFormat
    private String email;

    @JsonFormat(pattern = "dd MMM yyyy")
    private LocalDate registrationDate;

    @JsonFormat
    private Set<Language> secondLanguage;

    public UserLoginResponse(Long userID, String username, String email, LocalDate registrationDate, Set<Language> secondLanguage) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.secondLanguage = secondLanguage;
    }
}
