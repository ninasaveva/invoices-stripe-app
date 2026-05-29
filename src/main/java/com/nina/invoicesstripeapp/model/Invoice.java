package com.nina.invoicesstripeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="invoices")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNumber;
    private BigDecimal amount;
    private String description;

    @Enumerated(EnumType.STRING)
    private InvoicesStatus status = InvoicesStatus.PENDING;

    private LocalDateTime createdAt;
    private LocalDateTime paidAt;

    private String stripePaymentUrl;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
