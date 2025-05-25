package id.ac.ui.cs.advprog.udehnihdashboard.dto.auth;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.RoleType;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class AuthAddRoleResponse {
    private boolean success;
    private String message;
    private long userId;
    private RoleType roleType;
    private LocalDateTime timestamp;

    public boolean getSuccess() {
        return success;
    }
}
