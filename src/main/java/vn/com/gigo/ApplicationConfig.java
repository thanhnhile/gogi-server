package vn.com.gigo;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.com.gigo.utils.AppConfig;

@Configuration
public class ApplicationConfig {
    @Bean
    public PulsarClient pulsarClient () throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl(AppConfig.PULSAR_SERVICE_URL)
                .build();
    }
}
