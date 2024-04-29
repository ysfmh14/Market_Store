package com.example.market_store.Controller;

import com.example.market_store.dto.PaymentPaypalDto;
import com.example.market_store.dto.PaymentPaypalExecutionDto;
import com.example.market_store.service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaypalController {
    private final PaypalService paypalService;

    @Autowired
    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }


    @GetMapping("/access-token")
    public String getAccessToken() {
        return paypalService.getAccessToken();
    }
    @PostMapping("/create-order")
    public String createOrder() {
        return paypalService.createOrder();
    }


}
