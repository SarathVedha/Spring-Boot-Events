package com.vedha.event;

import com.vedha.dto.SinglePaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NotificationEvent {

    private SinglePaymentDTO singlePaymentDTO;

    private String message;
}
