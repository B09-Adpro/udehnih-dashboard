package id.ac.ui.cs.advprog.udehnihdashboard.config;

import id.ac.ui.cs.advprog.udehnihdashboard.security.JwtAuthenticationFilter;
import id.ac.ui.cs.advprog.udehnihdashboard.security.UnauthenticatedEntryPoint;
import id.ac.ui.cs.advprog.udehnihdashboard.security.UnauthorizedAccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UnauthenticatedEntryPoint unauthenticatedEntryPoint;
    private final UnauthorizedAccessHandler unauthorizedAccessHandler;

    private static final String publicAccess = "/api/security-test/public-access";

    @Bean
    public RequestMatcher publicAccessMathcer() {
        return new AntPathRequestMatcher(publicAccess, "GET");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {

        jwtAuthenticationFilter.setPublicAccess(publicAccessMathcer());

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(publicAccess).permitAll()
                        .requestMatchers("/api/staff/**").hasRole("STAFF")
                        .anyRequest().authenticated()
                ).exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthenticatedEntryPoint)
                        .accessDeniedHandler(unauthorizedAccessHandler)
                );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
