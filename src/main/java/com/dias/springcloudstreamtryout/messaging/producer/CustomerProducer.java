package com.dias.springcloudstreamtryout.messaging.producer;

import com.dias.springcloudstreamtryout.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProducer {

    private static final String BIND_NAME = "customerConsumer-out-0";


    private final StreamBridge streamBridge;

    public void produce(Customer customer) {
        streamBridge.send(BIND_NAME, customer);
    }

}
