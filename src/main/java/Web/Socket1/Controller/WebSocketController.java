package Web.Socket1.Controller;

import Web.Socket1.Entities.MessageFromClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/broadcast-message")
    public ResponseEntity sendNotificationToClients(@RequestBody MessageFromClientDTO messageFromClientDTO){
        simpMessagingTemplate.convertAndSend("/topic/broadcast", messageFromClientDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
