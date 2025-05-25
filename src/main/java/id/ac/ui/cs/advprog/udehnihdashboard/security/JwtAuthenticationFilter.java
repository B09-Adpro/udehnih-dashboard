package id.ac.ui.cs.advprog.udehnihdashboard.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UnauthenticatedEntryPoint unathenticatedEntryPoint;

    @Setter
    private RequestMatcher publicAccess;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return publicAccess.matches(request);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        log.info("JwtAuthenticationFilter: doFilterInternal called for URI: {}", request.getRequestURI());
        log.info("Authorization Header: {}", request.getHeader("Authorization"));

        try {
            String jwt = parseJwt(request);
            boolean validToken = jwtUtil.validateToken(jwt);

            if (!StringUtils.hasText(jwt) || !validToken) {
                unathenticatedEntryPoint.commence(request, response,
                        new BadCredentialsException("Not authenticated"));
                return;
            }

            Claims claims = jwtUtil.extractClaims(jwt);

            String subjectStr = claims.getSubject();
            Long studentId = Long.parseLong(subjectStr);

            String email = claims.get("email", String.class);

            log.info("JWT Claims for user {}: {}", email, claims.toString());

            List<String> rolesFromClaim = claims.get("authorities", List.class);

            List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_STUDENT")
            );

            log.info("Extracted authorities for user {}: {}", email, authorities);

            if (rolesFromClaim != null) {
                authorities = rolesFromClaim.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
                log.debug("User {} has authorities from token: {}", email, authorities);
            } else {
                log.warn("No 'authorities' claim found in JWT for user {}. Assigning no specific authorities.", email);
            }

            AppUserDetails studentDetails = new AppUserDetails(
                    studentId,
                    email,
                    authorities
            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(studentDetails, jwt, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("User {} successfully authenticated with authorities: {}",
                    studentDetails.getUsername(),
                    authentication.getAuthorities());

        } catch (Exception e) {
            log.error("Cannot set authentication: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
