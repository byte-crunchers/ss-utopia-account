package com.ssutopia.financial.accountService.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreditLimitDto {
    private Long cardNum;
    private int creditLimit;
}
