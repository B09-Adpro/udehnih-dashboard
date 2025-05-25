package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorApplicationTest {

    private TutorApplication tutorApplicationResponse;
    private LocalDateTime time;

    @BeforeEach
    public void setUp() {
        this.tutorApplicationResponse = new TutorApplication();
        tutorApplicationResponse.setApplicationId(123L);
        tutorApplicationResponse.setStudentId("student123");
        tutorApplicationResponse.setExperience("old");
        tutorApplicationResponse.setQualification("very smart");
        tutorApplicationResponse.setBio("single");
        tutorApplicationResponse.setStatus(ApplicationStatus.PENDING);

        time = LocalDateTime.now();

        tutorApplicationResponse.setSubmittedAt(time);
        tutorApplicationResponse.setProcessedAt(time);
    }

    @Test
    public void testGetAttributes() {
        assertEquals(123L, tutorApplicationResponse.getApplicationId());
        assertEquals("student123", tutorApplicationResponse.getStudentId());
        assertEquals("old", tutorApplicationResponse.getExperience());
        assertEquals("very smart", tutorApplicationResponse.getQualification());
        assertEquals("single", tutorApplicationResponse.getBio());
        assertEquals(ApplicationStatus.PENDING, tutorApplicationResponse.getStatus());
        assertEquals(time, tutorApplicationResponse.getSubmittedAt());
        assertEquals(time, tutorApplicationResponse.getProcessedAt());
    }
}
