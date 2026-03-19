package lab.trading.trade.config;

import java.net.http.HttpClient;
import java.time.Duration;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RiskClientConfig {

    @Bean
    RestClient riskRestClient(
            @Value("${app.risk.base-url}") String baseUrl,
            @Value("${app.risk.connect-timeout-ms}") int connectTimeoutMs,
            @Value("${app.risk.read-timeout-ms}") int readTimeoutMs
    ) {
        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(
                HttpClient.newBuilder()
                        .connectTimeout(Duration.ofMillis(connectTimeoutMs))
                        .build()
        );
        factory.setReadTimeout(Duration.ofMillis(readTimeoutMs));

        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .requestInterceptor((request, body, execution) -> {
                    String correlationId = MDC.get("correlationId");
                    if (correlationId != null) {
                        request.getHeaders().add("X-Correlation-Id", correlationId);
                    }
                    return execution.execute(request, body);
                })
                .build();
    }
}
