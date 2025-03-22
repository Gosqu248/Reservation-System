package pl.urban.authservice.response;


public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        String role
) {
}
