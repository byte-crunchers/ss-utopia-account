package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String accountName;

    @Builder.Default
    private int fee = 0;

    @Builder.Default
    private float apy = 0.0f;

    @Builder.Default
    private float taxes = 0.0f;

    private int contributionLimits;

    private int withdrawalLimits;

    private int withdrawalAgeLimits;



}
