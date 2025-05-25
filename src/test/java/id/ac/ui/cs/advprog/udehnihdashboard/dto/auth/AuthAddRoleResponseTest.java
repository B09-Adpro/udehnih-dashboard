package id.ac.ui.cs.advprog.udehnihdashboard.dto.auth;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthAddRoleResponseTest {

    private AuthAddRoleResponse response;
    private LocalDateTime timestamp;

    @BeforeEach
    public void setUp() {
        this.response = new AuthAddRoleResponse();
        response.setSuccess(true);
        response.setMessage("sucess");
        response.setUserId(123L);
        response.setRoleType(RoleType.TUTOR);

        timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);
    }

    @Test
    public void testGetAttributes() {
        assertEquals(true, response.getSuccess());
        assertEquals("success", response.getMessage());
        assertEquals(123L, response.getUserId());
        assertEquals(RoleType.TUTOR, response.getRoleType());
        assertEquals(timestamp, response.getTimestamp());
    }
}
