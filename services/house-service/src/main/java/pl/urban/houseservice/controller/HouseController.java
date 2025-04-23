package pl.urban.houseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.urban.houseservice.response.HouseResponse;
import pl.urban.houseservice.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("/api/house")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService service;

    @GetMapping("/{house-id}")
    public ResponseEntity<HouseResponse> getHouse(
            @PathVariable("house-id") Long houseId
    ) {
        return ResponseEntity.ok(service.findById(houseId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HouseResponse>> getAllHouses() {
        return ResponseEntity.ok(service.findAll());
    }
}
