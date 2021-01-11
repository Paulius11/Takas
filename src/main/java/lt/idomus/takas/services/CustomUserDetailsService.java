package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.repository.UserRepository;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<ArticleUser> user = repository.findByUsername(s);
        if (user.isEmpty()) throw new UsernameNotFoundException("Username not found with username'" + s + "' ");

        return user.map(CustomUserDetails::new).get();
    }

    public UserDetails loadUserByEmail(String s) throws UsernameNotFoundException {

        Optional<ArticleUser> user = repository.findByEmail(s);
        if (user.isEmpty()) throw new UsernameNotFoundException("Username not found with email'" + s + "' ");

        return user.map(CustomUserDetails::new).get();
    }

    @Transactional
    public ArticleUser loadUserById(Long id) {

        ArticleUser user = repository.getById(id);

        if (user == null) throw new UsernameNotFoundException("Username not found with id'" + id + "' ");

        return new CustomUserDetails(user);
    }
}
