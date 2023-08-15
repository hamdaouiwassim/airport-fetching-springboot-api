package com.airportFetching.airportfetching.service;

// Java Program to Illustrate Creation Of
// Service Interface


import com.airportFetching.airportfetching.email.EmailDetails;

// Interface
public interface EmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
