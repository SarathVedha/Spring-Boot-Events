package com.vedha.service.impl;

import com.vedha.dto.SinglePaymentDTO;
import com.vedha.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class EmailNotificationImpl implements NotificationService {

    @Override
    public void sendSinglePaymentNotification(SinglePaymentDTO singlePaymentDTO, String message) throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        log.warn("Email Notification id: {}, status: {}" , singlePaymentDTO.getId(), message);
    }
}
