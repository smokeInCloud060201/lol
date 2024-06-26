package vn.com.lol.samira.common.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consume(User user) {
        System.out.println("Received message: " + user);
    }
}
