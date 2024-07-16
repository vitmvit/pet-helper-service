package by.vitikova.discovery.feign;

import by.vitikova.discovery.UserDto;
import by.vitikova.discovery.auth.JwtDto;
import by.vitikova.discovery.auth.SignInDto;
import by.vitikova.discovery.auth.SignUpCreateDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Feign-клиент для взаимодействия с микросервисом аутентификации
 */
@FeignClient(contextId = "authClient", value = "${feign.auth-service.value}", url = "${feign.auth-service.url}")
public interface AuthClient {

    /**
     * Регистрирует нового пользователя.
     *
     * @param dto данные для регистрации пользователя
     * @return данные зарегистрированного пользователя
     */
    @PostMapping("/signUp")
    ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpCreateDto dto);

    /**
     * Выполняет вход пользователя в систему.
     *
     * @param dto данные для входа пользователя
     * @return JWT-токен
     */
    @PostMapping(value = "/signIn")
    ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto dto);

    /**
     * Проверяет действительность токена аутентификации.
     *
     * @param auth токен аутентификации, переданный в заголовке "Authorization"
     * @return объект ResponseEntity со статусом ответа и результатом проверки (true, если токен действителен, иначе - false)
     */
    @PostMapping("/check")
    ResponseEntity<Boolean> check(@RequestHeader("Authorization") String auth);
}