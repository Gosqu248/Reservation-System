package pl.urban.reservationservice.service;

import org.springframework.stereotype.Service;
import pl.urban.reservationservice.entity.Reservation;
import pl.urban.reservationservice.enums.ReservationStatus;
import pl.urban.reservationservice.request.ReservationRequest;
import pl.urban.reservationservice.response.ReservationResponse;

import java.math.BigDecimal;

@Service
public class ReservationMapper {
    public Reservation toReservation(ReservationRequest request, BigDecimal totalPrice) {
        return Reservation.builder()
                .houseId(request.houseId())
                .userId(request.userId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .totalPrice(totalPrice)
                .status(ReservationStatus.PENDING)
                .build();
    }

    public ReservationResponse toReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getHouseId(),
                reservation.getUserId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getTotalPrice(),
                reservation.getStatus().name()
        );
    }
}
