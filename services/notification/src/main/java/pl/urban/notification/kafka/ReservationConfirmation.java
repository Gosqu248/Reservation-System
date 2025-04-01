package pl.urban.notification.kafka;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.urban.notification.response.HouseResponse;
import pl.urban.notification.response.UserResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationConfirmation(
        Long id,
        HouseResponse house,
        UserResponse user,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate endDate,
        BigDecimal totalPrice,
        String status
) {
}
