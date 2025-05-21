package id.ac.ui.cs.advprog.udehnihdashboard.dto;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.PaymentStatus;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Payment payment;

    @BeforeEach
    public void setUp() {
        this.payment = new Payment();
        this.payment.setTransactionId("trans123");
        this.payment.setUserId("user123");
        this.payment.setUserName("Jane Doe");
        this.payment.setCourseId("course123");
        this.payment.setCourseTitle("Introduction to Python");
        this.payment.setTutorName("Ben Dover");
        this.payment.setAmount(100000);
        this.payment.setStatus(PaymentStatus.PENDING.getValue());
        this.payment.setMethod(PaymentMethod.BANK_TRANSFER.getValue());
        this.payment.setCreatedAt(LocalDateTime.parse("2025-05-20T14:30:00"));
    }

    @Test
    public void testGetAttributes(){
        assertEquals("trans123", payment.getTransactionId());
        assertEquals("user123", payment.getUserId());
        assertEquals("Jane Doe", payment.getUserName());
        assertEquals("course123", payment.getCourseId());
        assertEquals("Introduction to Python", payment.getCourseTitle());
        assertEquals("Ben Dover", payment.getTutorName());
        assertEquals(100000, payment.getAmount());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(LocalDateTime.parse("2025-05-20T14:30:00"), payment.getCreatedAt());
    }
}
