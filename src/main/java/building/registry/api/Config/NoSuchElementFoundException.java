package building.registry.api.Config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchElementFoundException extends ResponseStatusException {

    public NoSuchElementFoundException(){
        super(HttpStatus.NOT_FOUND, "Item not found!");
    }
}
