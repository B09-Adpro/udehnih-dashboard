package id.ac.ui.cs.advprog.udehnihdashboard.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hamcrest.Matchers;

import java.security.Key;
import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key key;

    @BeforeEach
    void setUp() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateTestJwtToken(Long id, String email, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("email", email);
        claims.put("authorities", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void testPublicAccess() throws Exception {
        mockMvc.perform(get("/api/security-test/public-access")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Public access. What are you doing here?"));
    }

    @Test
    void testAnyRoleNoToken() throws Exception {
        mockMvc.perform(get("/api/security-test/any-role")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message")
                        .value("Not authenticated"));
    }

    @Test
    void testAnyRoleInvalidToken() throws Exception {
        String invalidToken = "InvalidTokenForTesting.123456789qwertyasdf";

        mockMvc.perform(get("/api/security-test/any-role")
                        .header("Authorization", "Bearer " + invalidToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message")
                        .value(Matchers.containsString(
                                "Not authenticated")));
    }

    @Test
    void testAnyRoleStudentToken() throws Exception {
        String studentToken = generateTestJwtToken(1L, "student@example.com",
                Collections.singletonList("STUDENT"));

        mockMvc.perform(get("/api/security-test/any-role")
                        .header("Authorization", "Bearer " + studentToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value(Matchers.containsString(
                                "User ID: 1, email: student@example.com, roles: ROLE_STUDENT")));
    }

    @Test
    void testAnyRoleStaffToken() throws Exception {
        String staffToken = generateTestJwtToken(2L, "staff@example.com",
                Collections.singletonList("STAFF"));

        mockMvc.perform(get("/api/security-test/any-role")
                        .header("Authorization", "Bearer " + staffToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value(Matchers.containsString(
                                "User ID: 2, email: staff@example.com, roles: ROLE_STAFF")));
    }

    @Test
    void testStaffOnlyStudentToken() throws Exception {
        String studentToken = generateTestJwtToken(1L, "student@example.com",
                Collections.singletonList("STUDENT"));

        mockMvc.perform(get("/api/security-test/staff-only")
                    .header("Authorization", "Bearer " + studentToken)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message")
                        .value("Not authorized"));
    }

    @Test
    void testStaffOnlyStaffToken() throws Exception {
        String staffToken = generateTestJwtToken(2L, "staff@example.com",
                Collections.singletonList("STAFF"));

        mockMvc.perform(get("/api/security-test/staff-only")
                        .header("Authorization", "Bearer " + staffToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value(Matchers.containsString(
                                "User ID: 2, email: staff@example.com, roles: ROLE_STAFF")));
    }
}
