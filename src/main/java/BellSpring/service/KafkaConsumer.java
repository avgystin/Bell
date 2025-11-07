package BellSpring.service;

import BellSpring.model.MessageEntity;
import BellSpring.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final MessageRepository messageRepository;

    @KafkaListener(topics = "postedmessages", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);

        // Сохраняем в PostgreSQL
        MessageEntity entity = new MessageEntity();
        entity.setContent(message);
        messageRepository.save(entity);

        log.info("Message saved to database with id: {}", entity.getId());
    }
}
