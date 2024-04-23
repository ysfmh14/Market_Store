package com.example.market_store.service;

public interface MailSenderService {
    void sendNewMail(String to, String subject, String body) ;
}
