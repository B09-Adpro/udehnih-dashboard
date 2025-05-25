package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateApplicationRequestTest {

    private UpdateApplicationRequest request;

    @BeforeEach
    public void setUp() {
        request = new UpdateApplicationRequest();
        request.setStatus(ApplicationStatus.ACCEPTED);
        request.setNotes("nice");
    }

    @Test
    public void testGetAttributes() {
        assertEquals(ApplicationStatus.ACCEPTED, request.getStatus());
        assertEquals("nice", request.getNotes());
    }
}
