package pl.urban.authservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailExistException extends RuntimeException {
    private final String msg;

}
