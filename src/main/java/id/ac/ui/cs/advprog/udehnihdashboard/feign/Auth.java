package id.ac.ui.cs.advprog.udehnihdashboard.feign;

import id.ac.ui.cs.advprog.udehnihdashboard.dto.auth.AuthUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="auth", url="auth-temp.com")
public interface Auth {

    @RequestMapping(method = RequestMethod.GET, value="/api/users/{userId}")
    AuthUserInfoResponse getUserInfo(@PathVariable("userId") String userId);

}
