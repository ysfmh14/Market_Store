package com.example.market_store.Controller;

import com.example.market_store.dto.StripeChargeDto;
import com.example.market_store.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor

public class StripeController {
    private final StripeService stripeService;

    @PostMapping("/charge")
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {
        return stripeService.charge(model);
    }


}

