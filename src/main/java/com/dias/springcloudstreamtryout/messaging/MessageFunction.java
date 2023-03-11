package com.dias.springcloudstreamtryout.messaging;

import com.dias.springcloudstreamtryout.model.Customer;
import com.dias.springcloudstreamtryout.model.RequestForCustomer;
import com.dias.springcloudstreamtryout.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageFunction {

    private final CustomerService customerService;

    @Bean
    public Function<Flux<RequestForCustomer>, Flux<Customer>> convertRequestToCustomers() {
        return requestForCustomer -> requestForCustomer.map(RequestForCustomer::quantity)
                .map(customerService::generateCustomer)
                .flatMap(Flux::fromIterable)
                .doOnError(error -> log.error("Erro happened", error))
                .onErrorContinue((error, obj) -> log.error("Erro happened", error));
    }

}
