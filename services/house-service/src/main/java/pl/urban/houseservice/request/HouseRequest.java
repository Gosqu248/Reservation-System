package pl.urban.houseservice.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pl.urban.houseservice.enums.HouseType;
import pl.urban.houseservice.enums.LocationType;

import java.math.BigDecimal;
import java.util.List;

public record HouseRequest(
    @NotNull(message = "Name is required")
    String name,
    @NotNull(message = "Description is required")
    String description,
    @NotNull(message = "House type is required")
    HouseType type,
    @Positive(message = "Capacity must be positive")
    Integer capacity,
    @Positive(message = "Price must be positive")
    BigDecimal price,

    @NotNull(message = "Location type is required")
    LocationType locationType,
    List<String> amenities

) {
}
