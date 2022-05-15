package jpwp.jpwpproject.DTOs;

import jpwp.jpwpproject.util.Language;

public class RegistrationRequest {
    private final String username;
    private final String email;
    private final String password;
    private final Language secondLanguage;

    public RegistrationRequest(String username, String email, String password, Language secondLanguage) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.secondLanguage = secondLanguage;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Language getSecondLanguage() {
        return secondLanguage;
    }
}
