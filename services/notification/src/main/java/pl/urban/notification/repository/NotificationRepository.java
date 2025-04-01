package pl.urban.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.urban.notification.entity.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
