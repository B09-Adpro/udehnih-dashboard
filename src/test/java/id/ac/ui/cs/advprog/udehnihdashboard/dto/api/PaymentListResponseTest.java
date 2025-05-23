package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentListResponseTest {

    private PaymentListResponse paymentListResponse;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    public void setUp() {
        this.payment1 = new Payment();
        this.payment1.setTransactionId("transaction1");
        this.payment2 = new Payment();
        this.payment2.setTransactionId("transaction2");

        List<Payment> paymentList = new ArrayList<Payment>();
        paymentList.add(payment1);
        paymentList.add(payment2);

        this.paymentListResponse = new PaymentListResponse();
        this.paymentListResponse.setPaymentList(paymentList);
    }

    @Test
    void testGetAttributes(){
        List<Payment> paymentList = paymentListResponse.getPaymentList();
        Iterator<Payment> iterator = paymentList.iterator();

        Payment payment = iterator.next();
        assertEquals(payment1.getTransactionId(), payment.getTransactionId());

        payment = iterator.next();
        assertEquals(payment2.getTransactionId(), payment.getTransactionId());
    }
}
