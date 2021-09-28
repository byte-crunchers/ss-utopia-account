package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String card_num;

    private int pin;

    private int cvc1;

    private int cvc2;

    private Date exp_date;

    @ManyToOne
    @JoinColumn(
            name = "accounts_id")
    private Accounts accounts;



}
