package id.ac.ui.cs.advprog.udehnihdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UdehnihDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdehnihDashboardApplication.class, args);
    }

}
