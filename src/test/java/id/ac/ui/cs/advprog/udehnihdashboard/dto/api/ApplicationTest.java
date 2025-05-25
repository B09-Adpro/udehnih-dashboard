package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    private Application application;

    @BeforeEach
    void setUp() {
        this.application = new Application();
        this.application.setApplicationId("application123");
        this.application.setUserId("user123");
        this.application.setName("John Doe");
        this.application.setEmail("john@example.com");
        this.application.setExperience("rookie");
        this.application.setQualifications("have internet");
        this.application.setStatus("PENDING");
        this.application.setSubmittedAt("2023-05-15T10:30:00Z");
    }

    @Test
    void testGetAttributes() {
        assertEquals("application123", this.application.getApplicationId());
        assertEquals("user123", this.application.getUserId());
        assertEquals("John Doe", this.application.getName());
        assertEquals("john@example.com", this.application.getEmail());
        assertEquals("rookie", this.application.getExperience());
        assertEquals("have internet", this.application.getQualifications());
        assertEquals("PENDING", this.application.getStatus());
        assertEquals("2023-05-15T10:30:00Z", this.application.getSubmittedAt());
    }

}
