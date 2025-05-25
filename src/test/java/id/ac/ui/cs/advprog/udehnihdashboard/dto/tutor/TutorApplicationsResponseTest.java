package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorApplicationsResponseTest {

    private TutorApplicationsResponse response;
    private TutorApplication application1;
    private TutorApplication application2;

    @BeforeEach
    public void setUp() {
        this.application1 = new TutorApplication();
        this.application1.setApplicationId(1L);
        this.application2 = new TutorApplication();
        this.application2.setApplicationId(2L);

        List<TutorApplication> applicationList = new ArrayList<>();
        applicationList.add(this.application1);
        applicationList.add(this.application2);

        this.response = new TutorApplicationsResponse();
        this.response.setApplications(applicationList);
    }

    @Test
    void testGetAttributes() {
        List<TutorApplication> applicationList = response.getApplications();
        Iterator<TutorApplication> iterator = applicationList.iterator();

        TutorApplication application = iterator.next();
        assertEquals(application1.getApplicationId(), application.getApplicationId());

        application = iterator.next();
        assertEquals(application2.getApplicationId(), application.getApplicationId());
    }
}
