package ro.tuc.ds2020.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/sendNotification")
    @SendTo("/topic/notification")
    public String sendNotification(String notification) {
        return notification;
    }
}
