package com.example.market_store.Controller;

import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import com.example.market_store.service.PaymentService;
import com.example.market_store.service.SellerService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {
    private PaymentService paymentService;
    @GetMapping
    Page<ResponsePaymentDto> getPaymentByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                 @RequestParam(defaultValue = "10" , name = "size") int size,
                                                 @RequestParam( name = "id", required = false) Long id ,
                                                 @RequestParam(name = "paymentMode", required = false) String paymentMode ,
                                                 @RequestParam(name = "paymentCode", required = false) String paymentCode){

        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setId(id);
        paymentCriteria.setPaymentMode(paymentMode);
        paymentCriteria.setPaymentCode(paymentCode);
        return paymentService.findPaymentByCriteria(paymentCriteria,page,size);
    }


    @PostMapping
    public ResponsePaymentDto save(@RequestBody RequestPaymentDto requestPaymentDto){
        return paymentService.addPayment(requestPaymentDto);
    }
    @PutMapping
    public ResponsePaymentDto update(@RequestBody RequestPaymentDto requestPaymentDto){
        return paymentService.UpdatePayment(requestPaymentDto);
    }

    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        paymentService.deletePayment(id);
    }
    @PostMapping("/pdf")
    public void exportToPDF(HttpServletResponse response, @RequestParam(name ="paymentCode") String paymentCode) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        paymentService.generatePdf(response,paymentCode);

    }

}
