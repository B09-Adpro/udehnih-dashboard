package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorCoursesResponseTest {

    private TutorCoursesResponse response;
    private TutorCourse course1;
    private TutorCourse course2;

    @BeforeEach
    public void setUp() {
        this.course1 = new TutorCourse();
        this.course1.setCourseId(1L);
        this.course2 = new TutorCourse();
        this.course2.setCourseId(2L);

        List<TutorCourse> courses = new ArrayList<TutorCourse>();
        courses.add(course1);
        courses.add(course2);

        this.response = new TutorCoursesResponse();
        this.response.setCourses(courses);
    }

    @Test
    void testGetAttributes() {
        List<TutorCourse> courses = response.getCourses();
        Iterator<TutorCourse> iterator = courses.iterator();

        TutorCourse course = iterator.next();
        assertEquals(course1.getCourseId(), course.getCourseId());

        course = iterator.next();
        assertEquals(course2.getCourseId(), course.getCourseId());
    }
}
