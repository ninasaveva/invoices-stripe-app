package com.nina.invoicesstripeapp.repository;

import com.nina.invoicesstripeapp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
