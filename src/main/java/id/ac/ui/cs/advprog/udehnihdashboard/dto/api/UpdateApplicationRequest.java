package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationRequest {
    private ApplicationStatus status;
    private String notes;
}
