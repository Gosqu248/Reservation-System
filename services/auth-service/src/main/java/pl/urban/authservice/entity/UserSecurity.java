package pl.urban.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_security")
public class UserSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int failedLoginAttempts = 0;
    private LocalDateTime lastFailedLoginAttempt;
    private String twoFactorCode;
    private Long twoFactorCodeExpireTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
