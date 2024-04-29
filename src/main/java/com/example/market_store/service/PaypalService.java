package com.example.market_store.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    public String getAccessToken();
    public String createOrder();
//    public Payment executePayment(
//            String paymentId,
//            String payerId
//    )throws PayPalRESTException;
}
