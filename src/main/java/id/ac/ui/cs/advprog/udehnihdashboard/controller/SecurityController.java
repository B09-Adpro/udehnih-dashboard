package id.ac.ui.cs.advprog.udehnihdashboard.controller;

import id.ac.ui.cs.advprog.udehnihdashboard.dto.security.SecurityResponse;
import id.ac.ui.cs.advprog.udehnihdashboard.security.AppUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/security-test")
public class SecurityController {

    @GetMapping("/public-access")
    public ResponseEntity<SecurityResponse> publicAccess() {
        return ResponseEntity.ok(new SecurityResponse("Public access. What are you doing here?"));
    }

    @GetMapping("/any-role")
    public ResponseEntity<SecurityResponse> anyRole(
            @AuthenticationPrincipal AppUserDetails currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body(
                    new SecurityResponse("Not authenticated")
            );
        }

        List<String> roles = currentUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String message = String.format(
                "User ID: %d, email: %s, roles: %s",
                currentUser.getId(), currentUser.getEmail(),
                String.join(", ", roles)
        );

        return ResponseEntity.ok(new SecurityResponse(message));
    }

    @GetMapping("/staff-only")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<SecurityResponse> staffOnly(
            @AuthenticationPrincipal AppUserDetails currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body(
                    new SecurityResponse("Not authenticated")
            );
        }

        List<String> roles = currentUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String message = String.format(
                "User ID: %d, email: %s, roles: %s",
                currentUser.getId(), currentUser.getEmail(),
                String.join(", ", roles)
        );

        return ResponseEntity.ok(new SecurityResponse(message));
    }

}
