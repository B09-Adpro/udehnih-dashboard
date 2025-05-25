package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import lombok.Data;

@Data
public class TutorUpdateCourseRequest {
    private CourseStatus newStatus;
    private String feedback;
}
