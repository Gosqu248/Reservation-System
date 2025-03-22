package pl.urban.authservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountLockedException extends RuntimeException {
    private final String msg;

}
