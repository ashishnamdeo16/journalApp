package com.crazym416.journalApp;

import com.crazym416.journalApp.service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        System.out.println("testSendMail triggered!");
        emailService.sendEmail(
                "ashish.namdeo.528@my.csun.edu",
                "Checking Brother",
                "Just chill"
        );
    }
}
