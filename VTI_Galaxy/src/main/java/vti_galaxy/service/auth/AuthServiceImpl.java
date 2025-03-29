package vti_galaxy.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vti_galaxy.configs.jwt.JwtTokenUtils;
import vti_galaxy.modal.entity.Account;
import vti_galaxy.modal.response.AuthResponse;
import vti_galaxy.repository.AccountRepository;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final AccountRepository accountRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.accountRepository = accountRepository;
    }

    @Override
    public AuthResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account = accountRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Account not found")
        );
        String token = jwtTokenUtils.generateToken(userDetails);
        return new AuthResponse(account.getId(), token, email, account.getAvatar(), userDetails.getAuthorities());
    }
}
