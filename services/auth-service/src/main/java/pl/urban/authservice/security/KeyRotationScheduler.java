package pl.urban.authservice.security;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KeyRotationScheduler {
    private final JwtUtil jwtUtil;

    @Scheduled(cron = "0 0 0 * * SUN") // Co niedzielę o północy
    private void rotateKeys() {
        jwtUtil.rotateKey();
    }

}
