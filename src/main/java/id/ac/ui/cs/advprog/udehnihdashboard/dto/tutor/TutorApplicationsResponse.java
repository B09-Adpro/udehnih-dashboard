package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import lombok.Data;

import java.util.List;

@Data
public class TutorApplicationsResponse {
    private List<TutorApplicationResponse> applications;
}
