package com.nina.invoicesstripeapp.service;

import com.nina.invoicesstripeapp.model.Client;
import com.nina.invoicesstripeapp.model.Invoice;
import com.nina.invoicesstripeapp.model.InvoicesStatus;
import com.nina.invoicesstripeapp.repository.ClientRepository;
import com.nina.invoicesstripeapp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final StripeService stripeService;
    private final EmailService emailService;
    private final ClientRepository clientRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    public Invoice saveInvoice(Invoice invoice) throws Exception {
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus(InvoicesStatus.PENDING);
        invoice.setPaymentToken(UUID.randomUUID().toString());
        Invoice saved = invoiceRepository.save(invoice);
        String paymentUrl = stripeService.createCheckoutSession(saved);
        saved.setStripePaymentUrl(paymentUrl);
        Invoice result = invoiceRepository.save(saved);

        Client client = clientRepository.findById((long) invoice.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        emailService.sendPaymentConfirmation(
                client.getEmail(),
                invoice.getInvoiceNumber(),
                invoice.getAmount().doubleValue()
        );

        return result;
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