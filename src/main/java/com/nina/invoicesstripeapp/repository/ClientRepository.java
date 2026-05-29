package com.nina.invoicesstripeapp.repository;

import com.nina.invoicesstripeapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
