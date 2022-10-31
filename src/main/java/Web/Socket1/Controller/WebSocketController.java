package Web.Socket1.Controller;

import Web.Socket1.Entities.MessageFromClientDTO;
import Web.Socket1.Entities.SimpleMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/notification")
    public ResponseEntity sendNotificationToClients(@RequestBody SimpleMessageDTO simpleMessageDTO){
        simpMessagingTemplate.convertAndSend("/topic/messages", simpleMessageDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public SimpleMessageDTO handleMessageFromWebSocket(MessageFromClientDTO messageFromClientDTO){
        System.out.println("Arrived something on /app/hello - " + messageFromClientDTO.toString());
        return new SimpleMessageDTO("Message from " + messageFromClientDTO.getFrom() + " " + messageFromClientDTO.getText());
    }
}
