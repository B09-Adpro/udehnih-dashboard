package id.ac.ui.cs.advprog.udehnihdashboard.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="auth", url="https://udehnih-please-bisa-env.eba-uvfzaim3.us-east-1.elasticbeanstalk.com/")
public interface Auth {

}
