package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import lombok.Data;

@Data
public class TutorUpdateApplicationRequest {
    private ApplicationStatus newStatus;
    private String feedback;
}
