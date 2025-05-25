package id.ac.ui.cs.advprog.udehnihdashboard.service;

import id.ac.ui.cs.advprog.udehnihdashboard.client.Auth;
import id.ac.ui.cs.advprog.udehnihdashboard.client.Tutor;
import id.ac.ui.cs.advprog.udehnihdashboard.config.ThreadPoolConfig;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.GenericResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.Application;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.ApplicationsResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.UpdateApplicationResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.api.UpdateCourseResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.auth.AuthUserInfoResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorApplication;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorApplicationsResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorUpdateApplicationRequest;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorUpdateCourseRequest;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    private Auth auth;
    private Tutor tutor;
    private ExecutorService executor;

    public StaffServiceImpl(Auth auth, Tutor tutor) {
        this.auth = auth;
        this.tutor = tutor;
        this.executor = new ThreadPoolConfig().authInfoThreadPool();
    }

    @Override
    public ApplicationsResponse getAllTutorApplications() {
        TutorApplicationsResponse tutorResponse = tutor.getAllTutorApplications(ApplicationStatus.PENDING);
        List<TutorApplication> tutorApplications = tutorResponse.getApplications();

        if (tutorApplications.isEmpty()) {
            ApplicationsResponse response = new ApplicationsResponse();
            List<Application> applications = new ArrayList<>();
            response.setApplications(applications);
            return response;
        }

        Set<String> uniqueStudentIds = tutorApplications.stream()
                .map(TutorApplication::getStudentId)
                .collect(Collectors.toSet());

        List<CompletableFuture<AuthUserInfoResponse>> userInfoFutures = uniqueStudentIds.stream()
                .map(studentId -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return auth.getUserInfo(studentId);
                    } catch (Exception e) {
                        log.error("Failed to fetch user info for studentId {}: {}", studentId, e.getMessage(), e);
                        return null;
                    }
                }, executor))
                .collect(Collectors.toList());

        CompletableFuture.allOf(userInfoFutures.toArray(new CompletableFuture[0])).join();

        Map<String, AuthUserInfoResponse> userInfoMap = userInfoFutures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("Error retrieving user info from future: {}", e.getMessage(), e);
                        return null;
                    }
                })
                .filter(userInfo -> userInfo != null)
                .collect(Collectors.toMap(AuthUserInfoResponse::getId, Function.identity()));

        List<Application> applications = tutorApplications.stream()
                .map(app -> {
                    Application application = new Application();
                    application.setApplicationId(String.valueOf(app.getApplicationId()));
                    application.setUserId(app.getStudentId());

                    AuthUserInfoResponse userInfo = userInfoMap.get(app.getStudentId());
                    if (userInfo != null) {
                        application.setName(userInfo.getName());
                        application.setEmail(userInfo.getEmail());
                    } else {
                        log.warn("User info not found for studentId: {} after parallel fetch. Setting defaults.", app.getStudentId());
                        application.setName("Unknown User");
                        application.setEmail("unknown@example.com");
                    }

                    application.setExperience(app.getExperience());
                    application.setQualifications(app.getQualification());
                    application.setStatus(app.getStatus());
                    application.setSubmittedAt(app.getSubmittedAt());
                    return application;
                })
                .collect(Collectors.toList());

        ApplicationsResponse response = new ApplicationsResponse();
        response.setApplications(applications);

        return response;
    }

    @Override
    public UpdateApplicationResponse updateApplication(
            long applicationId, ApplicationStatus status, String notes
    ) {
        TutorUpdateApplicationRequest request = new TutorUpdateApplicationRequest();
        request.setNewStatus(status);
        request.setFeedback(notes);

        try {
            tutor.updateApplicationStatus(applicationId, request);

            UpdateApplicationResponse response = new UpdateApplicationResponse();
            response.setMessage("Application status updated successfully.");
            response.setApplicationId(String.valueOf(applicationId));
            response.setStatus(status);

            return response;
        } catch (Exception e) {
            log.error("Error updating application: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public UpdateCourseResponse updateCourse(
            long courseId, CourseStatus status, String notes
    ) {
        TutorUpdateCourseRequest request = new TutorUpdateCourseRequest();
        request.setNewStatus(status);
        request.setFeedback(notes);

        try {
            GenericResponse tutorResponse = tutor.updateCourseStatus(courseId, request);

            UpdateCourseResponse response = new UpdateCourseResponse();
            response.setMessage(tutorResponse.getMessage());
            response.setCourseId(String.valueOf(courseId));
            response.setStatus(status);
            return response;
        } catch (Exception e) {
            log.error("Error updating course: {}", e.getMessage(), e);
            return null;
        }
    }
}
