package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ro.tuc.ds2020.entities.Notification;

@Controller
@CrossOrigin
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/sendNotification")
    @SendTo("/topic/notification")
    public void receiveNotification(Notification notification) {
        System.out.println("Received Notification: " + notification.getMessage());
        messagingTemplate.convertAndSend("/topic/notification/" + notification.getToPersonId(), notification);
    }
}
