package vn.com.lol.samira.common.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final KafkaProducer kafkaProducer;


    @GetMapping("/send")
    public void sendMessage(@RequestBody User user) {
        log.info("Send message {}", user);
        kafkaProducer.sendMessage(user);
    }
}
