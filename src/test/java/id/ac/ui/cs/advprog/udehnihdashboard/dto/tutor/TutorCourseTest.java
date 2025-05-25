package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorCourseTest {
    private TutorCourse course;
    private LocalDateTime time;

    @BeforeEach
    public void setUp() {
        this.course = new TutorCourse();
        course.setCourseId(1L);
        course.setTitle("Title1");
        course.setCategory("Category1");
        course.setPrice(new BigDecimal(1));
        course.setTutorId("tutor123");

        time = LocalDateTime.now();
        course.setCreatedAt(time);
        course.setUpdatedAt(time);

        course.setStatus(CourseStatus.PENDING);
        course.setSectionCount(3);
        course.setArticleCount(5);
    }

    @Test
    public void testGetAttributes() {
        assertEquals(1L, course.getCourseId());
        assertEquals("Title1", course.getTitle());
        assertEquals("Category1", course.getCategory());
        assertEquals(new BigDecimal(1), course.getPrice());
        assertEquals("tutor123", course.getTutorId());
        assertEquals(time, course.getCreatedAt());
        assertEquals(time, course.getUpdatedAt());
        assertEquals(CourseStatus.PENDING, course.getStatus());
        assertEquals(3, course.getSectionCount());
        assertEquals(5, course.getArticleCount());
    }
}
