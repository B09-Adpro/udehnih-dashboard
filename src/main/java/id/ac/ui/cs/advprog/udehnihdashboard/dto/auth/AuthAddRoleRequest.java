package id.ac.ui.cs.advprog.udehnihdashboard.dto.auth;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.RoleType;
import lombok.Data;

@Data
public class AuthAddRoleRequest {
    private long id;
    private RoleType roleType;
}
