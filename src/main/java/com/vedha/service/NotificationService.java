package com.vedha.service;

import com.vedha.dto.SinglePaymentDTO;

public interface NotificationService {

    void sendSinglePaymentNotification(SinglePaymentDTO singlePaymentDTO, String message) throws InterruptedException;
}
