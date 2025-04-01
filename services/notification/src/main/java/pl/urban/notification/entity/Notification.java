package pl.urban.notification.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.urban.notification.enums.NotificationType;
import pl.urban.notification.kafka.ReservationConfirmation;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    public String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private ReservationConfirmation reservationConfirmation;
}
