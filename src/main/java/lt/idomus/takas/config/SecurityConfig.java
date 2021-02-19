package lt.idomus.takas.config;

import lombok.AllArgsConstructor;
import lt.idomus.takas.oauth.OAuth2LoginSuccessHandler;
import lt.idomus.takas.security.JwtAuthenticationEntryPoint;
import lt.idomus.takas.security.JwtAuthenticationFilter;
import lt.idomus.takas.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static lt.idomus.takas.constant.SecurityConstant.*;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true, // @Secured(ROLE_ADMIN)
        jsr250Enabled = true, // @RolesAllowed(ROLE_ADMIN, ROLES_USER)
        prePostEnabled = true
)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final PasswordEncoder passwordEncoder;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //HOME_PATH is defined in SecurityConstant class
                .antMatchers(HOME_PATH).permitAll()
                .antMatchers(USER_PATH).permitAll()
                .antMatchers(GET_ALL_ARTICLE_LIST).permitAll()
                // Permit swagger
                .antMatchers(SWAGGER_PATH).permitAll()
                .antMatchers(H2_PATH).permitAll()
                // Oauth2
                .antMatchers("/", "/error", "/webjars/**").permitAll()
                .mvcMatchers("/user").permitAll()
                .mvcMatchers("/user_details").permitAll()
                // actuator
                .mvcMatchers("/beans/**").permitAll()
                .mvcMatchers("/health/**").permitAll()
                .mvcMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable();

        http
                .oauth2Login()
                .successHandler(oAuth2LoginSuccessHandler);

        http.headers().frameOptions().disable(); // for H2-Console showing in browser
    }

}
