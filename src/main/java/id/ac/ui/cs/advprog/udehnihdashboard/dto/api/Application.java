package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import lombok.Data;

@Data
public class Application {
    private String applicationId;
    private String userId;
    private String name;
    private String email;
    private String experience;
    private String qualifications;
    private String status;
    private String submittedAt;
}
