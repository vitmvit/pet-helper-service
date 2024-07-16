package by.vitikova.discovery.config;

import by.vitikova.discovery.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурация безопасности для веб-приложения.
 * Определяет правила доступа к различным конечным точкам API и включает фильтр безопасности для аутентификации и авторизации.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Настройка для игнорирования запросов авторизации.
     *
     * @return WebSecurityCustomizer для настройки игнорируемых запросов
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/swagger-ui/**",
                "/api/doc/**",
                "/v3/api-docs/**"
        );
    }

    /**
     * Конфигурирует правила доступа к различным конечным точкам API и добавляет фильтр безопасности.
     *
     * @param httpSecurity объект HttpSecurity для настройки правил доступа
     * @return SecurityFilterChain объект, представляющий цепочку фильтров безопасности
     * @throws Exception при возникновении ошибки во время выполнения конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/v1/events/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/events").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/eventDictionaries/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/eventDictionaries").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/favoriteArticles/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/favoriteArticles").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/parents/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/parents").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/notifications/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/notifications").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/notificationTimes/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/notificationTimes").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/pedigree/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/pedigree").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/recommender/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/recommender").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/records/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/records").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/states/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/states").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/dictionaries/**").hasRole("USER").requestMatchers(HttpMethod.GET, "/api/v1/dictionaries").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/api/v1/events/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/events").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/eventDictionaries/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/eventDictionaries").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/favoriteArticles/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/favoriteArticles").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/parents/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/parents").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/notifications/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/notifications").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/notificationTimes/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/notificationTimes").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/pedigree/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/pedigree").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/recommender/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/recommender").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/records/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/records").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/states/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/states").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/dictionaries/**").hasRole("USER").requestMatchers(HttpMethod.POST, "/api/v1/dictionaries").hasRole("USER")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/events/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/events").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/eventDictionaries/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/eventDictionaries").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/favoriteArticles/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/favoriteArticles").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/parents/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/parents").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/notifications/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/notifications").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/notificationTimes/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/notificationTimes").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/pedigree/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/pedigree").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/recommender/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/recommender").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/records/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/records").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/states/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/states").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/dictionaries/**").hasRole("USER").requestMatchers(HttpMethod.PUT, "/api/v1/dictionaries").hasRole("USER")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/events/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/events").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/eventDictionaries/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/eventDictionaries").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/favoriteArticles/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/favoriteArticles").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/parents/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/parents").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/notifications/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/notifications").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/notificationTimes/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/notificationTimes").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/pedigree/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/pedigree").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/recommender/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/recommender").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/records/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/records").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/states/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/states").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/dictionaries/**").hasRole("USER").requestMatchers(HttpMethod.DELETE, "/api/v1/dictionaries").hasRole("USER")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Получает AuthenticationManager из AuthenticationConfiguration.
     *
     * @param authenticationConfiguration объект AuthenticationConfiguration, который предоставляет AuthenticationManager
     * @return AuthenticationManager объект для аутентификации
     * @throws Exception при возникновении ошибки во время получения AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}