package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCourseRequestTest {

    private UpdateCourseRequest request;

    @BeforeEach
    public void setUp() {
        request = new UpdateCourseRequest();
        request.setStatus(CourseStatus.PUBLISHED);
        request.setNotes("nice");
    }

    @Test
    public void testGetAttributes(){
        assertEquals(CourseStatus.PUBLISHED, request.getStatus());
        assertEquals("nice", request.getNotes());
    }
}
