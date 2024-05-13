package com.example.market_store.service;

import com.example.market_store.dto.StripeChargeDto;

public interface StripeService {
    public StripeChargeDto charge(StripeChargeDto chargeRequest);
}
