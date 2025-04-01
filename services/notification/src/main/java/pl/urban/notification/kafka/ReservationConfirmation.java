package pl.urban.notification.kafka;

import pl.urban.notification.response.HouseResponse;
import pl.urban.notification.response.UserResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationConfirmation(
        Long id,
        HouseResponse house,
        UserResponse user,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal totalPrice,
        String status
) {
}
