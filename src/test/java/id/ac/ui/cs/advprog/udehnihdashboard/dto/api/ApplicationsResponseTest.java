package id.ac.ui.cs.advprog.udehnihdashboard.dto.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationsResponseTest {
    private ApplicationsResponse tutorApplicationsResponse;
    private Application tutorApplication1;
    private Application tutorApplication2;

    @BeforeEach
    public void setUp() {
        this.tutorApplication1 = new Application();
        this.tutorApplication1.setApplicationId("application1");
        this.tutorApplication2 = new Application();
        this.tutorApplication2.setApplicationId("application2");

        List<Application> applicationList = new ArrayList<Application>();
        applicationList.add(tutorApplication1);
        applicationList.add(tutorApplication2);

        this.tutorApplicationsResponse = new ApplicationsResponse();
        this.tutorApplicationsResponse.setApplications(applicationList);
    }

    @Test
    void testGetAttributes(){
        List<Application> applicationList = tutorApplicationsResponse.getApplications();
        Iterator<Application> iterator = applicationList.iterator();

        Application application = iterator.next();
        assertEquals(tutorApplication1.getApplicationId(), application.getApplicationId());

        application = iterator.next();
        assertEquals(tutorApplication2.getApplicationId(), application.getApplicationId());
    }
}
