package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

    private Float balance;

    private Float debt_interest;

    private Float payment_due;

    private Date due_date;

    private Integer credit_limit;

    private Boolean active, approved, confirmed;

    @ManyToOne
    @JoinColumn(
            name = "users_id")
    private Users users;

//    @OneToMany(mappedBy = "accounts",cascade = CascadeType.ALL)
//    private List<Cards> cards;

    @ManyToOne
    @JoinColumn(
            name = "account_type")
    private AccountTypes accountTypes;

}
