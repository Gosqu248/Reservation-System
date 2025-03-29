package pl.urban.reservationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urban.reservationservice.exception.BusinessException;
import pl.urban.reservationservice.repository.ReservationRepository;
import pl.urban.reservationservice.request.ReservationRequest;
import pl.urban.reservationservice.response.ReservationResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final HouseClient houseClient;
    private final UserClient userClient;
    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    public String createReservation(ReservationRequest request) {
        var user = this.userClient.findUserById(request.userId())
                .orElseThrow(() -> new BusinessException("Cannot create reservation:: No user exists with the provided ID"));

        var house = this.houseClient.getHouseById(request.houseId())
                .orElseThrow(() -> new BusinessException("Cannot create reservation:: No house exists with the provided ID"));

        validateDates(request.startDate(), request.endDate());

        checkHouseAvailability(request.houseId(), request.startDate(), request.endDate());

        BigDecimal totalPrice = calculateTotalPrice(house.price(), request.startDate(), request.endDate());
        repository.save(mapper.toReservation(request, totalPrice));

        return "Utworzono rezerwację dla użytkownika: " + user.email() + " na dom: " + house.name() + " od " + request.startDate() + " do " + request.endDate();
    }

    private void validateDates(LocalDate start, LocalDate end) {
        if (start.isBefore(LocalDate.now())) {
            throw new BusinessException("Cannot create reservation:: Start date cannot be in the past");
        }
        if (end.isBefore(start)) {
            throw new BusinessException("Cannot create reservation:: End date must be after start date");
        }
    }

    private void checkHouseAvailability(Long houseId, LocalDate startDate, LocalDate endDate) {
        boolean hasConflicts  = this.repository.existsConflictingReservations(houseId, startDate, endDate);

        if (hasConflicts) {
            throw new BusinessException("Cannot create reservation:: House is not available for the selected dates");
        }
    }

    private BigDecimal calculateTotalPrice(BigDecimal price, LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end);
        return price.multiply(BigDecimal.valueOf(days));
    }

    public List<ReservationResponse> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toReservationResponse)
                .collect(Collectors.toList());
    }
}
