package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cards {
    @Id
    private Long card_num;

    private int pin;

    private int cvc1;

    private int cvc2;

    private LocalDate exp_date;

    @ManyToOne
    @JoinColumn(
            name = "accounts_id")
    private Accounts accounts;



}
