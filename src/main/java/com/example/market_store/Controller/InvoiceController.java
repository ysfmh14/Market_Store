package com.example.market_store.Controller;

import com.example.market_store.criteria.InvoiceCriteria;
import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.pdf.InvoicePdf;
import com.example.market_store.service.InvoiceService;
import com.example.market_store.service.PaymentService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;
    @GetMapping
    Page<ResponseInvoiceDto> getInvoiceByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                  @RequestParam(defaultValue = "10" , name = "size") int size,
                                                  @RequestParam( name = "id", required = false) Long id ,
                                                  @RequestParam(name = "invoiceCode", required = false) String invoiceCode){

        InvoiceCriteria invoiceCriteria = new InvoiceCriteria();
        invoiceCriteria.setId(id);
        invoiceCriteria.setInvoiceCode(invoiceCode);
        return invoiceService.findInvoiceByCriteria(invoiceCriteria,page,size);
    }


    @PostMapping
    public ResponseInvoiceDto save(@RequestBody RequestInvoiceDto requestInvoiceDto){
        return invoiceService.addInvoice(requestInvoiceDto);
    }


    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        invoiceService.deleteInvoice(id);
    }

}
