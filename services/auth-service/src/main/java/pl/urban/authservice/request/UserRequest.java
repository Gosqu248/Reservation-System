package pl.urban.authservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import pl.urban.authservice.enums.Role;

public record UserRequest(
         @NotNull(message = "Firstname is required")
         String firstname,
         @NotNull(message = "Lastname is required")
         String lastname,
         @NotNull(message = "Email is required")
         @Email(message = "Email is not valid email address")
         String email,
         @NotNull(message = "Phone number is required")
         String phoneNumber,
         @NotNull(message = "Password is required")
         String password,
         Role role
) {
}
