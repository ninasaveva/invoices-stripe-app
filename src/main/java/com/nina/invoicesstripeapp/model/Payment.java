package com.nina.invoicesstripeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stripePaymentId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private LocalDateTime paidAt;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}