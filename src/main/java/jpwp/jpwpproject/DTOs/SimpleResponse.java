package jpwp.jpwpproject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SimpleResponse {
    @JsonFormat
    private String messages;

    public SimpleResponse(String messages) {
        this.messages = messages;
    }
}
