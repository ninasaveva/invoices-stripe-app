package com.nina.invoicesstripeapp.service.impl;

import com.nina.invoicesstripeapp.model.Payment;
import com.nina.invoicesstripeapp.model.PaymentStatus;
import com.nina.invoicesstripeapp.repository.PaymentRepository;
import com.nina.invoicesstripeapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getByInvoiceId(Long invoiceId) {
        return paymentRepository.findByInvoiceId(invoiceId);
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Плаќањето не постои"));
    }

    @Override
    public Payment create(Payment payment) {
        if (payment.getStatus() == null) {
            payment.setStatus(String.valueOf(PaymentStatus.PENDING));
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updateStatus(Long id, PaymentStatus status) {
        Payment existing = getById(id);
        existing.setStatus(String.valueOf(status));
        return paymentRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}