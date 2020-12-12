package lt.idomus.takas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static lt.idomus.takas.security.SecurityConstant.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //HOME_PATH is defined in SecurityConstant class
                .antMatchers(HOME_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable();
    }
}
