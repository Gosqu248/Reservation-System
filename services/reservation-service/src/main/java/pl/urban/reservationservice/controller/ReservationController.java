package pl.urban.reservationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.urban.reservationservice.request.ReservationRequest;
import pl.urban.reservationservice.response.ReservationResponse;
import pl.urban.reservationservice.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;

    @PostMapping
    public ResponseEntity<String> createReservation(
            @RequestBody @Valid ReservationRequest request
    ) {
        return ResponseEntity.ok(service.createReservation(request));
    }

    @GetMapping("/all/{user-id}")
    public ResponseEntity<List<ReservationResponse>> getReservation(
            @PathVariable("user-id") Long userId
    ) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(service.findAll());
    }

}
