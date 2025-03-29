package pl.urban.reservationservice.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationResponse(
        Long id,
        Long houseId,
        Long userId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal totalPrice,
        String status
) {
}
