package vn.com.gigo.notification;

import org.apache.pulsar.client.api.PulsarClientException;

public interface Sender {
	void sendNotification(Notification notification) throws PulsarClientException;
}
