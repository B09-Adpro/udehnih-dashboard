package id.ac.ui.cs.advprog.udehnihdashboard.client;

import id.ac.ui.cs.advprog.udehnihdashboard.config.FeignConfig;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.GenericResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorApplicationsResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorCoursesResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorUpdateApplicationRequest;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorUpdateCourseRequest;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name="tutor", url="tutor-temp.com",
        configuration = FeignConfig.class
)
public interface Tutor {

    @GetMapping("/api/internal/tutor-applications")
    TutorApplicationsResponse getAllTutorApplications(
            @RequestParam(value="status", required=false) ApplicationStatus status
    );

    @PutMapping("/api/internal/{applicationId}/status")
    void updateApplicationStatus(
            @PathVariable("applicationId") Long applicationId,
            TutorUpdateApplicationRequest request
    );

    @GetMapping("/api/internal/course-applications")
    TutorCoursesResponse getAllCourseApplications();

    @PutMapping("/api/internal/course-applications/{courseId}/status")
    GenericResponse updateCourseStatus(
            @PathVariable("courseId") Long courseId,
            TutorUpdateCourseRequest request
    );
}
