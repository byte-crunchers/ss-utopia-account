package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private float balance;

    private float debt_interest;

    private float payment_due;

    private Date due_date;

    private int limit;

    private boolean active ;

    private boolean approved ;

    private boolean confirmed;

    @ManyToOne
    @JoinColumn(
            name = "users_id")
    private Users users;

    @OneToOne
    @JoinColumn(
            name = "account_type")
    private AccountTypes accountTypes;

}
