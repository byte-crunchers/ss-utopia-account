package com.ssutopia.financial.accountService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
//import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTypesDto {
    @Builder.Default
    private float late_fee = 0.0f;

    @Builder.Default
    private float savings_interest = 0.0f;

    @Builder.Default
    private float cashBack = 0.0f;

    @Builder.Default
    private float annual_fee = 0.0f;
    @Builder.Default
    private float foodie_pts = 0.0f;
}
