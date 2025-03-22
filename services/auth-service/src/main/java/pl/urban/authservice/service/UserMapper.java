package pl.urban.authservice.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.urban.authservice.entity.User;
import pl.urban.authservice.entity.UserSecurity;
import pl.urban.authservice.request.UserRequest;
import pl.urban.authservice.response.UserResponse;

@Component
@AllArgsConstructor
public class UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;

    public User toUser(UserRequest request) {
        User user =  User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .build();

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUser(user);
        user.setUserSecurity(userSecurity);

        return user;
    }

    public UserResponse fromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole().name()
        );
    }
}
