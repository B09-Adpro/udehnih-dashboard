package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TutorApplication {
    private long applicationId;
    private String studentId;
    private String experience;
    private String qualification;
    private String bio;
    private ApplicationStatus status;
    private LocalDateTime submittedAt;
    private LocalDateTime processedAt;
}
