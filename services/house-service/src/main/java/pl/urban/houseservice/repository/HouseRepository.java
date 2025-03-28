package pl.urban.houseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.urban.houseservice.entity.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}
