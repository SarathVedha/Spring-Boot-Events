package com.vedha.service;

import com.vedha.dto.SinglePaymentDTO;

import java.util.Map;

public interface PaymentService {

    Map<String, String> makeSinglePayment(SinglePaymentDTO singlePaymentDTO);
}
