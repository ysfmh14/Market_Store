package com.example.market_store.service.Impl;

import com.example.market_store.service.PaypalService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PaypalServiceImpl implements PaypalService {
    private final RestTemplate restTemplate;
    private final APIContext apiContext;
    @Autowired
    public PaypalServiceImpl(RestTemplate restTemplate, APIContext apiContext) {
        this.restTemplate = restTemplate;
        this.apiContext = apiContext;
    }
@Override
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(apiContext.getClientID(), apiContext.getClientSecret());
        // Set up the request body
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "client_credentials");

    // Configuration de la requête
    HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

    // Envoi de la requête POST
    ResponseEntity<String> responseEntity = restTemplate.exchange(
            "https://api-m.sandbox.paypal.com/v1/oauth2/token",
            HttpMethod.POST,
            requestEntity,
            String.class);

     if (responseEntity.getStatusCode() == HttpStatus.OK) {
        // Traitement de la réponse JSON
        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            // Gérer les erreurs de parsing JSON
            e.printStackTrace();
            return null;
        }
    } else {
        // Gérer les erreurs ici
        return null;
    }
    }
    public String createOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("PayPal-Request-Id", "7b92603e-77ed-4896-8e78-5dea2050476a");
        headers.setBearerAuth(getAccessToken());

        String requestBody = "{ \"intent\": \"CAPTURE\", \"purchase_units\": [ { \"reference_id\": \"d9f80740-38f0-11e8-b467-0ed5f89f718b\", \"amount\": { \"currency_code\": \"USD\", \"value\": \"100.00\" }, \"shipping\": { \"address\": { \"address_line_1\": \"123 Main St\", \"address_line_2\": \"\", \"admin_area_2\": \"San Jose\", \"admin_area_1\": \"CA\", \"postal_code\": \"95131\", \"country_code\": \"US\" } } } ], \"application_context\": { \"shipping_preference\": \"SET_PROVIDED_ADDRESS\", \"user_action\": \"PAY_NOW\", \"return_url\": \"https://example.com/returnUrl\", \"cancel_url\": \"https://example.com/cancelUrl\" } }";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://api-m.sandbox.paypal.com/v2/checkout/orders",
                HttpMethod.POST,
                requestEntity,
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return responseEntity.getBody();
        } else {
            return "Error occurred: " + responseEntity.getStatusCode();
        }
    }

}
