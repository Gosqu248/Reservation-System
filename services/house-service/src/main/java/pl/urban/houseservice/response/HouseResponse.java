package pl.urban.houseservice.response;

import pl.urban.houseservice.enums.HouseType;
import pl.urban.houseservice.enums.LocationType;

import java.math.BigDecimal;
import java.util.List;

public record HouseResponse(
        Long id,
        String name,
        String description,
        HouseType type,
        Integer capacity,
        BigDecimal price,
        LocationType locationType,
        List<String> amenities
) {
}
