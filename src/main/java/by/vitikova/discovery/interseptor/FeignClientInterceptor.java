package by.vitikova.discovery.interseptor;

import by.vitikova.discovery.feign.AuthClient;
import by.vitikova.discovery.util.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Перехватчик для Feign клиента для добавления заголовка Authorization в исходящие запросы.
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);
    @Autowired
    private AuthClient authClient;

    public void apply(RequestTemplate template) {
        if (RequestContextHolder.getRequestAttributes() != null && RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String authorization = request.getHeader("Authorization");
            logger.info("Authorization: " + authorization);
            if (StringUtils.isNotEmpty(authorization)) {
                template.header("Authorization", authorization);
            }
        }
    }
}