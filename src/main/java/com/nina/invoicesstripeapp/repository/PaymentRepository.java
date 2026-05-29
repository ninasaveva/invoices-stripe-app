package com.nina.invoicesstripeapp.repository;

import com.nina.invoicesstripeapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}