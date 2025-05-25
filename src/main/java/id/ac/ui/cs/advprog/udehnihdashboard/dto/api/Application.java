package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Application {
    private String applicationId;
    private String userId;
    private String name;
    private String email;
    private String experience;
    private String qualifications;
    private ApplicationStatus status;
    private LocalDateTime submittedAt;
}
