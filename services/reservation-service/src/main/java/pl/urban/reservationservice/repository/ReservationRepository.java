package pl.urban.reservationservice.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.urban.reservationservice.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r WHERE " +
            "r.houseId = :houseId AND " +
            "r.status <> 'CANCELLED' AND " +
            "(r.startDate < :endDate AND r.endDate > :startDate)")
    boolean existsConflictingReservations(
            @Param("houseId") Long houseId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Reservation> findAllByUserId(Long userId);
}
