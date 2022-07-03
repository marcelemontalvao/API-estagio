package br.com.states.statesAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegionInvalidException extends Throwable {

    public RegionInvalidException(String message) {
        super(message);
    }
}
