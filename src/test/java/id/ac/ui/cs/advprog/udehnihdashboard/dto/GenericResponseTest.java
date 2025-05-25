package id.ac.ui.cs.advprog.udehnihdashboard.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericResponseTest {

    private GenericResponse response;

    @BeforeEach
    public void setUp() {
        this.response = new GenericResponse();
        response.setMessage("test123");
    }

    @Test
    public void testGetAttribute() {
        assertEquals("test123", response.getMessage());
    }
}
