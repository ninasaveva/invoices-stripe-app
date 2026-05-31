package com.nina.invoicesstripeapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendPaymentConfirmation(String toEmail, String invoiceNumber, double amount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("✅ Плаќањето е успешно - Фактура " + invoiceNumber);
        message.setText(
                "Здраво!\n\n" +
                        "Вашето плаќање за фактура " + invoiceNumber + " е успешно обработено.\n" +
                        "Износ: $" + amount + "\n\n" +
                        "Благодариме!\n" +
                        "Invoices App"
        );
        mailSender.send(message);
    }
}