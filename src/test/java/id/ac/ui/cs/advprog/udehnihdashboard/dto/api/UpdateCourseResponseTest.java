package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCourseResponseTest {

    private UpdateCourseResponse response;

    @BeforeEach
    public void setUp() {
        response = new UpdateCourseResponse();
        response.setMessage("test");
        response.setStatus(CourseStatus.PUBLISHED);
        response.setCourseId("course123");
    }

    @Test
    public void testGetAttributes(){
        assertEquals("test", response.getMessage());
        assertEquals(CourseStatus.PUBLISHED, response.getStatus());
        assertEquals("course123", response.getCourseId());
    }
}
