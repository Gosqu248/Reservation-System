package pl.urban.reservationservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaReservation {

    @Bean
    public NewTopic reservationTopic() {
        return TopicBuilder
                .name("reservation-topic")
                .build();
    }

}
