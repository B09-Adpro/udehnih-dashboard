package id.ac.ui.cs.advprog.udehnihdashboard.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getCredentials() instanceof String) {
                String token = (String) authentication.getCredentials();
                template.header("Authorization", "Bearer " + token);
                log.info("Forwarding token from authentication credentials");
            } else {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    String authHeader = request.getHeader("Authorization");
                    if (authHeader != null && !authHeader.isEmpty()) {
                        template.header("Authorization", authHeader);
                        log.info("Forwarding token from request header");
                    } else {
                        log.warn("No authentication token found to forward!");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error in FeignRequestInterceptor: {}", e.getMessage(), e);
        }
    }
}