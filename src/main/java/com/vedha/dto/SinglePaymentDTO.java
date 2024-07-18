package com.vedha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Single Payment DTO")
public class SinglePaymentDTO {

    @Schema(description = "Unique identifier of the Single Payment.", example = "1")
    private Long id;

    @Schema(description = "Payment type of the Single Payment.", example = "CASH")
    private String paymentType;

    @Schema(description = "Amount of the Single Payment.", example = "100")
    private Integer amount;
}
