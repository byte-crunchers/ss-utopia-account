package com.ssutopia.financial.accountService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssutopia.financial.accountService.entity.CardPayment;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, Long> {
}