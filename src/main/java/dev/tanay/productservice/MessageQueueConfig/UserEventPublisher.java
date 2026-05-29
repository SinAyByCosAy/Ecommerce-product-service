package dev.tanay.productservice.MessageQueueConfig;

import dev.tanay.productservice.dtos.EmailMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventPublisher {
    private final KafkaTemplate<String, EmailMessageDto> kafkaTemplate;
    public void publishUserCreated(EmailMessageDto msg){
        kafkaTemplate.send(
                "user-signup-topic",
                msg.getEmail(),
                msg
        );
    }
}
