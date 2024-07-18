package com.vedha.controller;

import com.vedha.dto.SinglePaymentDTO;
import com.vedha.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Payment API")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Make single payment")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/single", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> makeSinglePayment(@RequestBody SinglePaymentDTO singlePaymentDTO) {

        return ResponseEntity.ok(paymentService.makeSinglePayment(singlePaymentDTO));
    }
}
