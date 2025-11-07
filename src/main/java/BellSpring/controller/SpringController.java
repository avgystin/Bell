package BellSpring.controller;

import BellSpring.service.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SpringController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/post-message")
    public ResponseEntity<String> calculateSquare(@RequestBody Map<String, String> request,
                                                  HttpServletRequest httpRequest) {
        String msg_id = request.get("msg_id");
        long unixtimestampMs = System.currentTimeMillis();
        String unixtimestamp = String.valueOf(unixtimestampMs / 1000);
        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI();

        return kafkaProducer.sendToKafka(msg_id, unixtimestamp, method, path);
    }

}