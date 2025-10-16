package com.algohire.backend.config;
import com.algohire.backend.security.filter.JwtFilter;
import com.algohire.backend.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/auth/v1/login",
                                "/auth/v1/refreshToken",
                                "/auth/v1/candidate/register",
                                "/auth/v1/recruiter/registerExistingcompany",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/webjars/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/candidate/**").hasRole("CANDIDATE")
                        .requestMatchers("/auth/v1/refresh").permitAll()
                        .requestMatchers("/api/users/skills").permitAll()
                        .requestMatchers("/job/{jobId}",
                                                    "/job/serchjob").permitAll()
                        .requestMatchers("/api/users/me/skills").hasRole("CANDIDATE")
                        .requestMatchers("/job/create",
                                                    "/job/recruter/jobs",
                                                    "/job/recruter/update/{jobid}",
                                                    "/job/recruter/delete/{jobId}").hasRole("RECRUITER")
                        .requestMatchers("/application/create").hasRole("CANDIDATE")
                        .requestMatchers("/application/candidate/**").hasRole("CANDIDATE")
                        .requestMatchers("/application/recruiter/**").hasRole("RECRUITER")
                        .requestMatchers("/recruiter/view/**").hasRole("RECRUITER")

                        .requestMatchers("/api/uploadToVm","/api/resume/get/{userId}").permitAll()
                        .requestMatchers("/api/company/search/{query}").permitAll()
                        .requestMatchers("api/users/status","/api/users/addCompany").permitAll()
                        .requestMatchers("api/users/me/profile/update").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }


}
