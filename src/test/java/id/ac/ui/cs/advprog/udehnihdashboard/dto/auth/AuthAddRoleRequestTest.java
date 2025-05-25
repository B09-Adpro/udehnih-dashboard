package id.ac.ui.cs.advprog.udehnihdashboard.dto.auth;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthAddRoleRequestTest {

    private AuthAddRoleRequest request;

    @BeforeEach
    public void setUp() {
        this.request = new AuthAddRoleRequest();
        this.request.setId(123L);
        this.request.setRoleType(RoleType.STUDENT);
    }

    @Test
    public void testGetAttributes() {
        assertEquals(123L, request.getId());
        assertEquals(RoleType.STUDENT, request.getRoleType());
    }
}
