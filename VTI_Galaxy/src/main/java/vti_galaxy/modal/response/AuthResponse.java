package vti_galaxy.modal.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AuthResponse {
    private int accountId;
    private String token;
    private String email;
    private String avatar;
    private Collection<? extends GrantedAuthority> position;
}
