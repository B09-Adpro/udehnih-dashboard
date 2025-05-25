package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorApplicationsResponseTest {

    private TutorApplicationsResponse response;
    private TutorApplicationResponse application1;
    private TutorApplicationResponse application2;

    @BeforeEach
    public void setUp() {
        this.application1 = new TutorApplicationResponse();
        this.application1.setApplicationId(1L);
        this.application2 = new TutorApplicationResponse();
        this.application2.setApplicationId(2L);

        List<TutorApplicationResponse> applicationList = new ArrayList<>();
        applicationList.add(this.application1);
        applicationList.add(this.application2);

        this.response = new TutorApplicationsResponse();
        this.response.setApplications(applicationList);
    }

    @Test
    void testGetAttributes() {
        List<TutorApplicationResponse> applicationList = response.getApplications();
        Iterator<TutorApplicationResponse> iterator = applicationList.iterator();

        TutorApplicationResponse application = iterator.next();
        assertEquals(application1.getApplicationId(), application.getApplicationId());

        application = iterator.next();
        assertEquals(application2.getApplicationId(), application.getApplicationId());
    }
}
