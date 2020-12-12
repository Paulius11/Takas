package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.doa.UserRepository;
import lt.idomus.takas.model.ArticleUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        ArticleUser user = repository.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException("Username not found with username'" + s + "' ");

        return user;
    }

    @Transactional
    public ArticleUser loadUserById(Long id) {

        ArticleUser user = repository.getById(id);

        if (user == null) throw new UsernameNotFoundException("Username not found with id'" + id + "' ");

        return user;
    }
}
