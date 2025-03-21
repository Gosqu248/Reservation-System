package pl.urban.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.urban.authservice.entity.UserSecurity;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
}
