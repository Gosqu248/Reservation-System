package pl.urban.notification.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.urban.notification.entity.Notification;
import pl.urban.notification.enums.NotificationType;
import pl.urban.notification.repository.NotificationRepository;
import pl.urban.notification.service.EmailService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "reservation-topic")
    public void consumeReservationSuccessNotification(ReservationConfirmation reservationConfirmation) throws MessagingException {
        log.info("Consuming the message from reservation-topic Topic:: {}", reservationConfirmation);
        repository.save(
                Notification.builder()
                        .type(NotificationType.RESERVATION_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .reservationConfirmation(reservationConfirmation)
                        .build()

        );
        var userName = reservationConfirmation.user().firstname() + " " + reservationConfirmation.user().lastname();
        var reservation = reservationConfirmation.house().name() + " od " + reservationConfirmation.startDate() + " do " + reservationConfirmation.endDate();

        emailService.sendEmail(
                reservationConfirmation.user().email(),
                userName,
                reservationConfirmation.totalPrice(),
                reservationConfirmation.id(),
                reservation

        );
    }
}
