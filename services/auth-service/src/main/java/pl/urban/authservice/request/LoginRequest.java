package pl.urban.authservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "Email is required")
        @Email(message = "Email is not valid email address")
        String email,
        @NotNull(message = "Password is required")
        String password
) {
}
