package pl.urban.notification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;
import static pl.urban.notification.enums.EmailTemplate.RESERVATION_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalPrice,
            Long reservationId,
            String reservationReference
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        messageHelper.setFrom("contact@gosqu.pl");
        final String templateName = RESERVATION_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalPrice", totalPrice);
        variables.put("reservationId", reservationId);
        variables.put("reservationReference", reservationReference);

        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(RESERVATION_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(message);
            log.info("INFO - Email successfully sent to: {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("WARN - Cannot send email to: {}", destinationEmail);
        }
    }
}
