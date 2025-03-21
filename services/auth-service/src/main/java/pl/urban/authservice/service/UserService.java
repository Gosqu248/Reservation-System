package pl.urban.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urban.authservice.entity.User;
import pl.urban.authservice.repository.UserRepository;
import pl.urban.authservice.request.UserRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public String registerUser(UserRequest request) {
        User user = mapper.toUser(request);
        User savedUser = repository.save(user);
        return "User " + savedUser.getEmail() + " registered successfully";
    }
}
