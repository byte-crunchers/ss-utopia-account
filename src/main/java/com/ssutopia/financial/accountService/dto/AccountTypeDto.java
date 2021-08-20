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
public class AccountTypeDto {
    @NotBlank
    private String accountName;

    private float taxes = 0.0f;

    private int fee = 0;

    private float apy = 0.0f;

    private int contributionLimits;

    private int withdrawalLimits;

    private int withdrawalAgeLimits;
}
