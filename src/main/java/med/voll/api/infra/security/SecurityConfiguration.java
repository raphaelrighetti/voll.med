package med.voll.api.infra.security;

import med.voll.api.security.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers("/**").hasRole("ADMIN")
                .requestMatchers(
                        HttpMethod.GET,
                        "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/pacientes", "/admins").permitAll()
                .requestMatchers(HttpMethod.GET, "/pacientes", "/medicos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/medicos").hasRole("MEDICO")
                .requestMatchers("/pacientes", "/pacientes/**").hasRole("PACIENTE")
                .requestMatchers("/medicos", "/medicos/**", "/rh", "/rh/**").hasRole("RH")
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
