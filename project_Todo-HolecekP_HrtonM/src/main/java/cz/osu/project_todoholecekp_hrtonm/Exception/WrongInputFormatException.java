package cz.osu.project_todoholecekp_hrtonm.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongInputFormatException extends ResponseStatusException {

    public WrongInputFormatException(String statusText) {
        super(HttpStatus.BAD_REQUEST, statusText);
    }
}
