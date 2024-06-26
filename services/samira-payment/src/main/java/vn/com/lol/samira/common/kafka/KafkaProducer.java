package vn.com.lol.samira.common.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "my-topic";


    public void sendMessage(User user) {
        kafkaTemplate.send(TOPIC, user);
    }

}
