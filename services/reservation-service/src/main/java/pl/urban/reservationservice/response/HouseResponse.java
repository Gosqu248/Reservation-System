package pl.urban.reservationservice.response;


import java.math.BigDecimal;

public record HouseResponse(
        Long id,
        String name,
        String description,
        String type,
        BigDecimal price
) {
}
