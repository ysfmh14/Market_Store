package com.example.market_store.service.Impl;

import com.example.market_store.criteria.InvoiceCriteria;
import com.example.market_store.dto.StripeChargeDto;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import com.example.market_store.entitie.Invoice;
import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.Payment;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.InvoiceMapper;
import com.example.market_store.repositorie.InvoiceRepo;
import com.example.market_store.repositorie.PaymentRepo;
import com.example.market_store.service.InvoiceService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepo invoiceRepo;
    private PaymentRepo paymentRepo;
    private InvoiceMapper invoiceMapper;
    @Override
    public Page<ResponseInvoiceDto> findInvoiceByCriteria(InvoiceCriteria invoiceCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Invoice> invoicePage = invoiceRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (invoiceCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),invoiceCriteria.getId()));
            }
            if (invoiceCriteria.getInvoiceCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("invoiceCode"),invoiceCriteria.getInvoiceCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return invoiceMapper.modelToDtos(invoicePage);
    }

    @Override
    public ResponseInvoiceDto addInvoice(RequestInvoiceDto requestInvoiceDto) {
        String generatedCodeInvoice = "INV" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Invoice invoiceToSave = invoiceMapper.dtoToModel(requestInvoiceDto);
        invoiceToSave.setInvoiceCode(generatedCodeInvoice);
        Optional<Payment> payment = paymentRepo.findById(requestInvoiceDto.getPaymentId());
        invoiceToSave.setPayment(payment.get());
        Optional<Invoice> existingInvoice = invoiceRepo.findByInvoiceCode(invoiceToSave.getInvoiceCode());
        if (existingInvoice.isPresent()) {
            throw new EntityAlreadyExisteException("Invoice already exists with id: " + requestInvoiceDto.getId());
        }
        Invoice savedInvoice = invoiceRepo.save(invoiceToSave);

        return invoiceMapper.modelToDto(savedInvoice);
    }

    @Override
    public ResponseInvoiceDto UpdateInvoice(RequestInvoiceDto requestInvoiceDto) {
        return null;
    }

    @Override
    public void deleteInvoice(long id) {
        Optional<Invoice> invoice = invoiceRepo.findById(id);
        if (invoice.isEmpty()){
            throw new EntityNotFoundException("Invoice Not Found ID :  "+id);
        }
        invoiceRepo.deleteById(id);


    }


}
