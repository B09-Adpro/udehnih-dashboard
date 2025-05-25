package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorUpdateCourseRequestTest {

    private TutorUpdateCourseRequest request;

    @BeforeEach
    public void setUp() {
        this.request = new TutorUpdateCourseRequest;
        request.setNewStatus(CourseStatus.PUBLISHED);
        request.setFeedback("very nice");
    }

    @Test
    public void testGetAttributes() {
        assertEquals(CourseStatus.PUBLISHED, request.getNewStatus());
        assertEquals("very nice", request.getFeedback());
    }
}
