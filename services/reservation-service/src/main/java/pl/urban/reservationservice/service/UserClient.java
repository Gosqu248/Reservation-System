package pl.urban.reservationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.urban.reservationservice.response.UserResponse;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${application.config.auth-url}"
)
public interface UserClient {
    @GetMapping("/{id}")
    Optional<UserResponse> findUserById(@PathVariable("id") Long id);

}
