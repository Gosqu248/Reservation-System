package pl.urban.houseservice.service;

import org.springframework.stereotype.Service;
import pl.urban.houseservice.entity.House;
import pl.urban.houseservice.request.HouseRequest;
import pl.urban.houseservice.response.HouseResponse;

@Service
public class HouseMapper {
    public House toHouse(HouseRequest request) {
        return House.builder()
                .name(request.name())
                .description(request.description())
                .type(request.type())
                .capacity(request.capacity())
                .price(request.price())
                .locationType(request.locationType())
                .amenities(request.amenities())
                .build();

    }

    public HouseResponse toHouseResponse(House house) {
        return new HouseResponse(
                house.getId(),
                house.getName(),
                house.getDescription(),
                house.getType(),
                house.getCapacity(),
                house.getPrice(),
                house.getLocationType(),
                house.getAmenities()
        );
    }
}
