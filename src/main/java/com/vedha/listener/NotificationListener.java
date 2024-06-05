package com.vedha.listener;

import com.vedha.event.NotificationEvent;
import com.vedha.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService emailNotificationImpl;

    private final NotificationService SMSNotificationImpl;

    @Async // This annotation is used to run the method asynchronously
    @EventListener // This annotation is used to listen to the event
    public void handleEmailNotificationEvent(NotificationEvent notificationEvent) throws InterruptedException {

        emailNotificationImpl.sendSinglePaymentNotification(notificationEvent.getSinglePaymentDTO(), notificationEvent.getMessage());
    }

    @Async
    @EventListener
    public void handleSMSNotificationEvent(NotificationEvent notificationEvent) throws InterruptedException {

        SMSNotificationImpl.sendSinglePaymentNotification(notificationEvent.getSinglePaymentDTO(), notificationEvent.getMessage());
    }

    // ContextRefreshedEvent is published when the ApplicationContext is started
    @Async
    @EventListener
    public void handleAppStartEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.error("Application started: {}", contextRefreshedEvent);
    }

    // ContextClosedEvent is published when the ApplicationContext is closed
    @Async
    @EventListener
    public void handleAppTerminateEvent(ContextClosedEvent contextClosedEvent) {

        log.error("Application stop: {}", contextClosedEvent);
    }
}
