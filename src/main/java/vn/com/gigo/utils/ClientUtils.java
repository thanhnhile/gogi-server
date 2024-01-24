package vn.com.gigo.utils;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class ClientUtils {
    public  static  PulsarClient initPulsarClient () throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl(AppConfig.PULSAR_SERVICE_URL)
                .build();
    }
}
