package id.ac.ui.cs.advprog.udehnihdashboard.controller;

import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.*;
import id.ac.ui.cs.advprog.udehnihdashboard.security.AppUserDetails;
import id.ac.ui.cs.advprog.udehnihdashboard.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@PreAuthorize("hasRole('STAFF')")
public class StaffController {
    private final StaffService staffService;

    StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/tutors/applications")
    public ResponseEntity<ApplicationsResponse> getAllApplicatoin(
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {
        ApplicationsResponse response = staffService.getAllTutorApplications();

        if (response == null) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/tutors/{applicationId}")
    public ResponseEntity<UpdateApplicationResponse> updateApplication(
            @PathVariable("applicationId") long applicationId,
            @Valid @RequestBody UpdateApplicationRequest request,
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {
        UpdateApplicationResponse response = staffService.updateApplication(
                applicationId, request.getStatus(), request.getNotes()
        );
        if (response == null) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/course/{courseId}")
    public ResponseEntity<UpdateCourseResponse> updateCourse(
            @PathVariable("courseId") long courseId,
            @Valid @RequestBody UpdateCourseRequest request,
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {
        UpdateCourseResponse response = staffService.updateCourse(
                courseId, request.getStatus(), request.getNotes()
        );

        if (response == null) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
