package id.ac.ui.cs.advprog.udehnihdashboard.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("PENDING"),
    PAID("PAID"),
    FAILED("FAILED");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
