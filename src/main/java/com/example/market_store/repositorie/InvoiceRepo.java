package com.example.market_store.repositorie;

import com.example.market_store.entitie.Invoice;
import com.example.market_store.entitie.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Long>, JpaSpecificationExecutor<Invoice> {
    Optional<Invoice> findByInvoiceCode(String invoiceCode);
}
