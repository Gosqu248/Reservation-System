package pl.urban.reservationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.urban.reservationservice.response.HouseResponse;

import java.util.Optional;

@FeignClient(
        name = "house-service",
        url = "${application.config.house-url}"
)
public interface HouseClient {
    @GetMapping("/{house-id}")
    Optional<HouseResponse> getHouseById(@PathVariable("house-id") Long houseId);
}
