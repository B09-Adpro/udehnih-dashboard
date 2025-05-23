package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {
    private List<Transaction> transactions;
}
