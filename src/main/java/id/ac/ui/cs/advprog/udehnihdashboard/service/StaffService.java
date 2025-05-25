package id.ac.ui.cs.advprog.udehnihdashboard.service;

import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.ApplicationsResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.UpdateApplicationResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.UpdateCourseResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;

public interface StaffService {
    ApplicationsResponse getAllTutorApplications();
    UpdateApplicationResponse updateApplication(long applicationId, ApplicationStatus status, String notes);
    UpdateCourseResponse updateCourse(long courseId, CourseStatus status, String notes);
}
