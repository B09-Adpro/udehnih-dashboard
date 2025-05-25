package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateApplicationResponseTest {

    private UpdateApplicationResponse response;

    @BeforeEach
    public void setUp() {
        response = new UpdateApplicationResponse();
        response.setMessage("test");
        response.setStatus(ApplicationStatus.ACCEPTED);
        response.setUserId("useri123");
    }

    @Test
    public void testGetAttributes() {
        assertEquals("test", response.getMessage());
        assertEquals(ApplicationStatus.ACCEPTED, response.getStatus());
        assertEquals("useri123", response.getUserId());
    }
}
