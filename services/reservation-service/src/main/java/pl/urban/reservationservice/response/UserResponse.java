package pl.urban.reservationservice.response;

public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
