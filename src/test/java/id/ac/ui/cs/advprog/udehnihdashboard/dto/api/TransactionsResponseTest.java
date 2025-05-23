package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsResponseTest {

    private TransactionsResponse paymentListResponse;
    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    public void setUp() {
        this.transaction1 = new Transaction();
        this.transaction1.setTransactionId("transaction1");
        this.transaction2 = new Transaction();
        this.transaction2.setTransactionId("transaction2");

        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);

        this.paymentListResponse = new TransactionsResponse();
        this.paymentListResponse.setTransactions(transactionList);
    }

    @Test
    void testGetAttributes(){
        List<Transaction> transactionList = paymentListResponse.getTransactions();
        Iterator<Transaction> iterator = transactionList.iterator();

        Transaction transaction = iterator.next();
        assertEquals(transaction1.getTransactionId(), transaction.getTransactionId());

        transaction = iterator.next();
        assertEquals(transaction2.getTransactionId(), transaction.getTransactionId());
    }
}
