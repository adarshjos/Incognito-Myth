package com.incognitoMyth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    private static final Logger log= Logger.getLogger(WebSocketConfig.class.getName());
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/icognitoMyth").withSockJS();
        log.log(Level.SEVERE,"registerStompEndpoints executed");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/fp");
        registry.setApplicationDestinationPrefixes("/app");
        log.log(Level.SEVERE,"configureMessageBroker executed");
    }
}
