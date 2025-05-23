package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorApplicationListResponseTest {
    private TutorApplicationListResponse tutorApplicationListResponse;
    private TutorApplication tutorApplication1;
    private TutorApplication tutorApplication2;

    @BeforeEach
    public void setUp() {
        this.tutorApplication1 = new TutorApplication();
        this.tutorApplication1.setApplicationId("application1");
        this.tutorApplication2 = new TutorApplication();
        this.tutorApplication2.setApplicationId("application2");

        List<TutorApplication> applicationList = new ArrayList<TutorApplication>();
        applicationList.add(tutorApplication1);
        applicationList.add(tutorApplication2);

        this.tutorApplicationListResponse = new TutorApplicationListResponse();
        this.tutorApplicationListResponse.setApplicationList(applicationList);
    }

    @Test
    void testGetAttributes(){
        List<TutorApplication> applicationList = tutorApplicationListResponse.getApplicationList();
        Iterator<TutorApplication> iterator = applicationList.iterator();

        TutorApplication application = iterator.next();
        assertEquals(tutorApplication1.getApplicationId(), application.getApplicationId());

        application = iterator.next();
        assertEquals(tutorApplication2.getApplicationId(), application.getApplicationId());
    }
}
