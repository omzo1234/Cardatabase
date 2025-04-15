import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Désactive CSRF si non nécessaire
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Autorise Swagger
                                .anyRequest().authenticated() // Exige une authentification pour toutes les autres requêtes
                )
                .formLogin(withDefaults()); // Active la redirection vers une page de connexion
        return http.build();
    }

    private Customizer<FormLoginConfigurer<HttpSecurity>> withDefaults() {
        
        throw new UnsupportedOperationException("Unimplemented method 'withDefaults'");
    }
}