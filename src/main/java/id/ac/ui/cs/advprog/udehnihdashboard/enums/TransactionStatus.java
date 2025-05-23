package id.ac.ui.cs.advprog.udehnihdashboard.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    PENDING("PENDING"),
    PAID("PAID"),
    FAILED("FAILED");

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }
}
