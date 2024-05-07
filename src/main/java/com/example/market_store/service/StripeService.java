package com.example.market_store.service;

import com.example.market_store.dto.StripeChargeDto;
import com.example.market_store.dto.StripeTokenDto;

public interface StripeService {
    public StripeTokenDto createCardToken(StripeTokenDto model);
    public StripeChargeDto charge(StripeChargeDto chargeRequest);
}
