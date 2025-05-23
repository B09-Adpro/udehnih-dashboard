package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {
    private String transactionId;
    private String userId;
    private String userName;
    private String courseId;
    private String courseTitle;
    private String tutorName;
    private int amount;
    private String status;
    private String method;
    private LocalDateTime createdAt;
}
