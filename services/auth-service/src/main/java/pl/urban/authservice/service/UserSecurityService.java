package pl.urban.authservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urban.authservice.entity.User;
import pl.urban.authservice.entity.UserSecurity;
import pl.urban.authservice.repository.UserSecurityRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserSecurityService {
    private UserSecurityRepository userSecurityRepository;

    public boolean isAccountLocked(User user) {
        UserSecurity userSecurity =  ensureUserSecurity(user);

        if (userSecurity.getFailedLoginAttempts() >= 5) {
            LocalDateTime lockTime = user.getUserSecurity().getLastFailedLoginAttempt().plusMinutes(10);
            return LocalDateTime.now().isBefore(lockTime);
        }
        return false;
    }
    public void incrementFailedLoginAttempts(User user) {
        UserSecurity userSecurity = ensureUserSecurity(user);
        userSecurity.setFailedLoginAttempts(userSecurity.getFailedLoginAttempts() + 1);
        userSecurity.setLastFailedLoginAttempt(LocalDateTime.now());
        userSecurityRepository.save(userSecurity);
    }
    private UserSecurity ensureUserSecurity(User user) {
        UserSecurity userSecurity = user.getUserSecurity();
        if (userSecurity == null) {
            userSecurity = new UserSecurity();
            userSecurity.setUser(user);
            userSecurityRepository.save(userSecurity);
        }
        return userSecurity;
    }

    public void resetFailedLoginAttempts(User user) {
        UserSecurity userSecurity = ensureUserSecurity(user);
        userSecurity.setFailedLoginAttempts(0);
        userSecurity.setLastFailedLoginAttempt(null);
        userSecurityRepository.save(userSecurity);
    }
}
