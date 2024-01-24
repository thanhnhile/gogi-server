package vn.com.gigo.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.mapstruct.OrderMapper;
import vn.com.gigo.repositories.OrderRepository;
import vn.com.gigo.utils.AppConfig;
import vn.com.gigo.utils.ClientUtils;
import vn.com.gigo.utils.OrderStatus;

@Service
public class OrderNotification implements Sender {

	private static final Logger logger
			= LoggerFactory.getLogger(OrderNotification.class);

    private static Producer<Notification> orderProducer;

	@Autowired
    public OrderNotification(PulsarClient pulsarClient) throws PulsarClientException {
        orderProducer = pulsarClient.newProducer(JSONSchema.of(Notification.class))
				.topic(AppConfig.ORDERS_TOPIC)
				.producerName("order-created")
				.create();
    }


    public void setNotification(Notification notification){
		sendNotification(notification);
	}

	@Override
	public void sendNotification(Notification notification) {
		try {
			orderProducer.newMessage()
					.value(notification)
					.eventTime(System.currentTimeMillis())
					.send();

			logger.info("Sent new order created: {}", notification.getContent());
		} catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }

    }
}
