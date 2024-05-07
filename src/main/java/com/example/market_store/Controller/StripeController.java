package com.example.market_store.Controller;

import com.example.market_store.dto.StripeChargeDto;
import com.example.market_store.dto.StripeTokenDto;
import com.example.market_store.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor

public class StripeController {
    private final StripeService stripeService;


    @PostMapping("/card/token")
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model) {
        return stripeService.createCardToken(model);
    }

    @PostMapping("/charge")
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {
        return stripeService.charge(model);
    }


}

