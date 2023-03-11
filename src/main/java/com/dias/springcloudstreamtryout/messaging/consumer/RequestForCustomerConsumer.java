package com.dias.springcloudstreamtryout.messaging.consumer;

import com.dias.springcloudstreamtryout.model.RequestForCustomer;
import com.dias.springcloudstreamtryout.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class RequestForCustomerConsumer implements Consumer<RequestForCustomer> {

    private final CustomerService customerService;

    @Override
    public void accept(RequestForCustomer requestForCustomer) {
        customerService.startProcess(requestForCustomer.quantity());
    }
}
