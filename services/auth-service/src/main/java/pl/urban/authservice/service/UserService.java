package pl.urban.authservice.service;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.urban.authservice.entity.User;
import pl.urban.authservice.entity.UserSecurity;
import pl.urban.authservice.exception.AccountLockedException;
import pl.urban.authservice.exception.EmailExistException;
import pl.urban.authservice.exception.InvalidCredentialsException;
import pl.urban.authservice.exception.UserNotFoundException;
import pl.urban.authservice.repository.UserRepository;
import pl.urban.authservice.request.LoginRequest;
import pl.urban.authservice.request.UserRequest;
import pl.urban.authservice.response.UserResponse;
import pl.urban.authservice.security.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserSecurityService userSecurityService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String registerUser(UserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new EmailExistException(format("Email %s already exists", request.email()));
        }
        User user = mapper.toUser(request);
        User savedUser = repository.save(user);
        return "User " + savedUser.getEmail() + " registered successfully";
    }

    public void updateUser(UserRequest request) {
        User user = repository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException(format("User with email %s not found", request.email())));
        mergerUser(user, request);
        repository.save(user);
    }

    private void mergerUser(User user, UserRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            user.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            user.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            user.setEmail(request.email());
        }
        if (StringUtils.isNotBlank(request.phoneNumber())) {
            user.setPhoneNumber(request.phoneNumber());
        }
        if (StringUtils.isNotBlank(request.password())) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }
    }

    public List<UserResponse> findAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromUser)
                .collect(Collectors.toList());
    }

    public Boolean existsByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public UserResponse findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::fromUser)
                .orElseThrow(() -> new UserNotFoundException(format("User with email %s not found", email)));
    }

    public void deleteUser(String email) {
        repository.deleteByEmail(email);
    }

    public String loginUser(LoginRequest loginRequest) {
        User user = getUserBySubject(loginRequest.email());
        if (user == null) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        if (userSecurityService.isAccountLocked(user))  {
            throw new AccountLockedException("Account is locked. Try again later.");
        }
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            userSecurityService.incrementFailedLoginAttempts(user);
            repository.save(user);
            throw new InvalidCredentialsException("Invalid credentials");
        }
        userSecurityService.resetFailedLoginAttempts(user);
        return jwtUtil.generateToken(user, false);
    }

    private User getUserBySubject(String subject) {
        return repository.findByEmail(subject)
                .orElseThrow(() -> new UserNotFoundException(format("User with email %s not found", subject)));
    }


}
