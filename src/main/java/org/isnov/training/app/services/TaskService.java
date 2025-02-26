package org.isnov.training.app.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.isnov.training.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;

import java.util.HashMap;


@Slf4j
@Service
public class TaskService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromUserMail;

    @Async("notificationTask")
    public void sendHtmlEmail(String templateName, String to, String subject, HashMap<String, Object> emailDetails) {
        try {
            // prepare Thymeleaf context
            Context context = new Context();
            for (String s : emailDetails.keySet()){
                context.setVariable(s, emailDetails.get(s));
            }

            // generate HTML
            String htmlContent = templateEngine.process(templateName, context);

            // create email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom(fromUserMail);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async("notificationTask")
    public void numberOfUserProperty(Integer numberUser) {
        try {
            Thread.sleep(10000);

            log.warn("Nombre de propietés : "+numberUser);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
