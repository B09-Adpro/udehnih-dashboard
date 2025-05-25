package id.ac.ui.cs.advprog.udehnihdashboard.client;

import id.ac.ui.cs.advprog.udehnihdashboard.config.FeignConfig;
import id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor.TutorApplicationsResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.enums.ApplicationStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name="tutor", url="tutor-temp.com",
        configuration = FeignConfig.class
)
public interface Tutor {

    @RequestMapping(method= RequestMethod.GET, value="/api/internal/tutor-applicaiotns")
    TutorApplicationsResponse getAllTutorApplications(
            @RequestParam(value="status", required=false)ApplicationStatus status
    );

}
