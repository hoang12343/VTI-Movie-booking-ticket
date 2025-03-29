package vti_galaxy.service.customUserDetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vti_galaxy.modal.entity.Account;
import vti_galaxy.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(
                () -> new NullPointerException("Account with email " + email + " not found")
        );
        return new User(
                email,
                account.getPassword(),
                AuthorityUtils.createAuthorityList(
                    account.getPosition().name()
                )
        );
    }
}
