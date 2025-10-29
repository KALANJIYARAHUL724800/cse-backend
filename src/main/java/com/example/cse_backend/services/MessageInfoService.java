package com.example.cse_backend.services;

import com.example.cse_backend.Dto.MessageInfoDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MessageInfoService {
    @Value("${spring.mail.username}")
    private String owner;

    private final JavaMailSender mailSender;

    public MessageInfoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendEmail(MessageInfoDto message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        String currentDateTime = now.format(formatter);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getEmail());
        simpleMailMessage.setFrom(owner);
        simpleMailMessage.setSubject("Forgot Password from CSE " + currentDateTime);
        String resetLink = "";
        simpleMailMessage.setText(
                "Dear User,\n\n" +
                        "We received a request to reset your password for your CSE Portal account.\n\n" +
                        "If you made this request, please click the link below to reset your password:\n" +
                        resetLink + "\n\n" +
                        "If you did not request a password reset, please ignore this email. Your account will remain secure.\n\n" +
                        "Thank you,\n" +
                        "CSE Portal Support Team"
        );

        try {
            mailSender.send(simpleMailMessage);
            System.out.println("✅ Email sent successfully to " + message.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
