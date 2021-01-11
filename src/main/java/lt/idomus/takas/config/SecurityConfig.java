package lt.idomus.takas.config;

import lombok.AllArgsConstructor;
import lt.idomus.takas.security.JwtAuthenticationEntryPoint;
import lt.idomus.takas.security.JwtAuthenticationFilter;
import lt.idomus.takas.services.CustomOAuth2UserService;
import lt.idomus.takas.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static lt.idomus.takas.security.SecurityConstant.*;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] SWAGGER_PATH = {"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};
    public static final String H2_PATH = "/h2-console/**";

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2Login().userInfoEndpoint()
                .and()
                .successHandler(oAuth2LoginSuccessHandler);
        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //HOME_PATH is defined in SecurityConstant class
                .antMatchers(HOME_PATH).permitAll()
                .antMatchers(USER_PATH).permitAll()
                .antMatchers(HttpMethod.GET, GET_ALL_ARTICLE_LIST).permitAll()
                // Permit swagger
                .antMatchers(SWAGGER_PATH).permitAll()
                .antMatchers(H2_PATH).permitAll()
                // Oauth2
                .antMatchers("/", "/error", "/webjars/**").permitAll()
                .mvcMatchers("/user").permitAll()
                .mvcMatchers("/user_details").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable();


        http.headers().frameOptions().disable(); // for H2-Console showing in browser
    }

}
