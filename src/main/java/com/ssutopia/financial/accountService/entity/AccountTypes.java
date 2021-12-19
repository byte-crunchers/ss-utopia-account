package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="account_types")
public class AccountTypes {
    @Id
    private String id;


    private float late_fee = 0.0f;


    private float savings_interest = 0.0f;

    @Builder.Default
    private float cashback = 0.0f;

    private float annual_fee = 0.0f;


    private float foodie_pts = 0.0f;




}
