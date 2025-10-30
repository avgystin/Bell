package Bell.BellSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpringService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public ResponseEntity<String> sendToKafka(String msg_id, String unixtimestamp,
                                 String method, String path) {

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("msg_id", msg_id);
        hashMap.put("timestamp", unixtimestamp);
        hashMap.put("method", method);
        hashMap.put("uri", path);

        try {
            kafkaTemplate.send("postedmessages", hashMap);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error");
        }
    }
}
