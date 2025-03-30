package pl.urban.reservationservice.kafka;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationConfirmation(
        Long id,
        Long houseId,
        Long userId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal totalPrice,
        String status
) {
}
