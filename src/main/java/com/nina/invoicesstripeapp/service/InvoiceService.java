package com.nina.invoicesstripeapp.service;

import com.nina.invoicesstripeapp.model.Invoice;
import com.nina.invoicesstripeapp.model.InvoicesStatus;
import com.nina.invoicesstripeapp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    public Invoice saveInvoice(Invoice invoice) {
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus(InvoicesStatus.PENDING);
        return invoiceRepository.save(invoice);
    }

    public Invoice markAsPaid(Long id) {
        Invoice invoice = getInvoiceById(id);
        invoice.setStatus(InvoicesStatus.PAID);
        invoice.setPaidAt(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}