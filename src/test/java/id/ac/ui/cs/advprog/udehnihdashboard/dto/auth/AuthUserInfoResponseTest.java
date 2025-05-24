package id.ac.ui.cs.advprog.udehnihdashboard.dto.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthUserInfoResponseTest {

    private UserInfo userInfo;

    @BeforeEach
    public void setUp() {
        this.userInfo = new UserInfo();
        this.userInfo.setId("user123");
        this.userInfo.setEmail("test@example.com");
        this.userInfo.setName("John Doe");
    }

    @Test
    public void testGetAttributes(){
        assertEquals("user123", userInfo.getId());
        assertEquals("test@example.com", userInfo.getEmail());
        assertEquals("John Doe", userInfo.getName());
    }
}
