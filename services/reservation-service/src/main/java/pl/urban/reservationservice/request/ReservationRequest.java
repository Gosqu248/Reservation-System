package pl.urban.reservationservice.request;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationRequest(
        @NotNull(message = "House ID is required")
        Long houseId,
        @NotNull(message = "User ID is required")
        Long userId,
        @NotNull(message = "Start date is required")
        @Future
        LocalDate startDate,
        @NotNull(message = "End date is required")
        @Future
        LocalDate endDate

) {
}
