package id.ac.ui.cs.advprog.udehnihdashboard.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    BANK_TRANSFER("Bank Transfer"),
    CREDIT_CARD("Credit Card");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }
}
