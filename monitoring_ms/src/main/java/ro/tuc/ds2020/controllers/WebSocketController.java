package ro.tuc.ds2020.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ro.tuc.ds2020.entities.Notification;

@Controller
public class WebSocketController {

    @MessageMapping("/sendNotification")
    @SendTo("/topic/notification")
    public Notification sendNotification(Notification notification) {
        return notification;
    }
}
