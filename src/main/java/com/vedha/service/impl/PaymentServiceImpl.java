package com.vedha.service.impl;

import com.vedha.dto.SinglePaymentDTO;
import com.vedha.event.NotificationEvent;
import com.vedha.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    // tightly coupled with EmailNotificationImpl and SMSNotificationImpl, 
    // imagine if we have a lot of notifications to send
//    private final EmailNotificationImpl emailNotification;

//    private final SMSNotificationImpl smsNotification;

    // loosely coupled with NotificationEvent, with this we can add more notifications without changing the code
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Map<String, String> makeSinglePayment(SinglePaymentDTO singlePaymentDTO) {

        try {

            if (singlePaymentDTO.getAmount() > 0) {

                // Notification blocking the payment process
                // imagine if we have a lot of notifications to send
//                emailNotification.sendSinglePaymentNotification(singlePaymentDTO, "success");
//                smsNotification.sendSinglePaymentNotification(singlePaymentDTO, "success");

                applicationEventPublisher.publishEvent(NotificationEvent.builder().singlePaymentDTO(singlePaymentDTO).message("success").build());
                return Map.of("status", "success", "message", "Payment successful");
            } else {

                // Notification blocking the payment process
//                emailNotification.sendSinglePaymentNotification(singlePaymentDTO, "failed");
//                smsNotification.sendSinglePaymentNotification(singlePaymentDTO, "failed");

                applicationEventPublisher.publishEvent(NotificationEvent.builder().singlePaymentDTO(singlePaymentDTO).message("failed").build());
                return Map.of("status", "failed", "message", "Payment failed");
            }
        } catch (Exception exception) {

            log.error("Error occurred while making payment: {}", exception.getMessage());
        }

        return Map.of("status", "failed", "message", "Payment failed");

    }
}
