package com.nina.invoicesstripeapp.service;

import com.nina.invoicesstripeapp.model.Payment;
import com.nina.invoicesstripeapp.model.PaymentStatus;
import java.util.List;

public interface PaymentService {

    List<Payment> getAll();

    List<Payment> getByInvoiceId(Long invoiceId);

    Payment getById(Long id);

    Payment create(Payment payment);

    Payment updateStatus(Long id, PaymentStatus status);

    void delete(Long id);
}