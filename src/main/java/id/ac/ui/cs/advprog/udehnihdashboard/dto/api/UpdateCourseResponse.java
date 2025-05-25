package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import lombok.Data;

@Data
public class UpdateCourseResponse {
    private String message;
    private String courseId;
    private CourseStatus status;
}
