package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorUpdateApplicationRequestTest {

    private TutorUpdateApplicationRequest request;

    @BeforeEach
    public void setUp() {
        this.request = new TutorUpdateApplicationRequest();
        request.setNewStatus(ApplicationStatus.ACCEPTED);
        request.setFeedback("very nice");
    }

    @Test
    public void testGetAttributes() {
        assertEquals(ApplicationStatus.ACCEPTED, request.getNewStatus());
        assertEquals("very nice", request.getFeedback());
    }
}
