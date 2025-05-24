package id.ac.ui.cs.advprog.udehnihdashboard.dto.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityResponseTest {

    private SecurityResponse response;

    @BeforeEach
    public void setUp() {
        this.response = new SecurityResponse();
        this.response.setMessage("test message");
    }

    @Test
    public void testGetAttributes() {
        assertEquals("test message", response.getMessage());
    }
}
