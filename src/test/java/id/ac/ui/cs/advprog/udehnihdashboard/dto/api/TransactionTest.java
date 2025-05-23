package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.TransactionStatus;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.TransactionMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        this.transaction = new Transaction();
        this.transaction.setTransactionId("trans123");
        this.transaction.setUserId("user123");
        this.transaction.setUserName("Jane Doe");
        this.transaction.setCourseId("course123");
        this.transaction.setCourseTitle("Introduction to Python");
        this.transaction.setTutorName("Ben Dover");
        this.transaction.setAmount(100000);
        this.transaction.setStatus(TransactionStatus.PENDING.getValue());
        this.transaction.setMethod(TransactionMethod.BANK_TRANSFER.getValue());
        this.transaction.setCreatedAt(LocalDateTime.parse("2025-05-20T14:30:00"));
    }

    @Test
    public void testGetAttributes(){
        assertEquals("trans123", transaction.getTransactionId());
        assertEquals("user123", transaction.getUserId());
        assertEquals("Jane Doe", transaction.getUserName());
        assertEquals("course123", transaction.getCourseId());
        assertEquals("Introduction to Python", transaction.getCourseTitle());
        assertEquals("Ben Dover", transaction.getTutorName());
        assertEquals(100000, transaction.getAmount());
        assertEquals(TransactionStatus.PENDING.getValue(), transaction.getStatus());
        assertEquals(TransactionMethod.BANK_TRANSFER.getValue(), transaction.getMethod());
        assertEquals(LocalDateTime.parse("2025-05-20T14:30:00"), transaction.getCreatedAt());
    }
}
