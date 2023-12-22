package ro.tuc.ds2020.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ro.tuc.ds2020.entities.Notification;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/sendNotification")
    @SendTo("/topic/notification")
    public void receiveNotification(Notification notification) throws JsonProcessingException {
        System.out.println("Received Notification: " + notification.getMessage() + " from " + notification.getFromPersonId() + " to " + notification.getToPersonId());

        // Convert Notification object to JSON
        String jsonNotification = new ObjectMapper().writeValueAsString(notification);

        // Store message in Redis
        redisTemplate.opsForList().rightPush(getConversationTopic(notification.getFromPersonId(), notification.getToPersonId()), jsonNotification);

        messagingTemplate.convertAndSend("/topic/notification/" + notification.getToPersonId(), notification.getMessage());
    }

    // Additional method to retrieve stored messages from Redis
    @MessageMapping("/getStoredMessages")
    @SendTo("/topic/notification")
    public void getStoredMessages(Map<String, Object> payload) {
        int fromPersonId = (int) payload.get("fromPersonId");
        int toPersonId = (int) payload.get("toPersonId");

        List<Object> storedMessages = redisTemplate.opsForList().range(getConversationTopic(fromPersonId, toPersonId), 0, -1);

        // Send stored messages to the user
        assert storedMessages != null;
        storedMessages.forEach(message ->
                messagingTemplate.convertAndSend("/topic/notification/" + fromPersonId, message));
    }

    private String getConversationTopic(int userId1, int userId2) {
        // Sort user IDs to make the hash order-agnostic
        int smallerUserId = Math.min(userId1, userId2);
        int largerUserId = Math.max(userId1, userId2);

        // Use a consistent hash for the conversation topic based on user IDs
        int hash = new HashCodeBuilder(17, 37) // 17 and 37 are arbitrary prime numbers
                .append(smallerUserId)
                .append(largerUserId)
                .toHashCode();
        System.out.println("Conversation topic hash: " + hash);
        return String.format("/topic/notification/conversation/%d", hash);
    }
}
