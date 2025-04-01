package pl.urban.notification.enums;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    RESERVATION_CONFIRMATION("reservation-confirmation.html", "Reservation Confirmation"),
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Confirmation")
    ;

    private final String template;
    private final String subject;

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
