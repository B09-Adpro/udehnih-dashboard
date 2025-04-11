package id.ac.ui.cs.advprog.udehnihdashboard.dto;

import lombok.Data;

@Data
public class TutorApplication {
    private String applicationId;
    private String userId;
    private String name;
    private String email;
    private String experience;
    private String qualifications;
    private String status;
    private String submittedAt;
}
