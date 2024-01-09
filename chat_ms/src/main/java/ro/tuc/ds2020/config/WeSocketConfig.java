package ro.tuc.ds2020.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WeSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/notification_topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // First endpoint
        registry.addEndpoint("/ws").setAllowedOrigins("http://client-ms-bv.aqeme8cygncrh0ec.switzerlandnorth.azurecontainer.io").withSockJS();

        // Second endpoint
        registry.addEndpoint("/notif").setAllowedOrigins("http://client-ms-bv.aqeme8cygncrh0ec.switzerlandnorth.azurecontainer.io").withSockJS();
    }
}
