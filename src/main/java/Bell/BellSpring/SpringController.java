package Bell.BellSpring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServlet;
import java.util.Map;

@RestController
public class SpringController {
    @Autowired
    private SpringService squareService;

    @PostMapping("/post-message")
    public ResponseEntity<String> calculateSquare(@RequestBody Map<String, String> request,
                                                  HttpServletRequest httpRequest) {
        String msg_id = request.get("msg_id");

        long unixtimestampMs = System.currentTimeMillis();
        String unixtimestamp = String.valueOf(unixtimestampMs / 1000);

        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI();

        return squareService.sendToKafka(msg_id, unixtimestamp, method, path);
    }

}