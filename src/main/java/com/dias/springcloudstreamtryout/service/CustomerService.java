package com.dias.springcloudstreamtryout.service;

import com.dias.springcloudstreamtryout.messaging.producer.CustomerProducer;
import com.dias.springcloudstreamtryout.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerProducer customerProducer;

    public void doSomething(Customer customer) {
        //log.info(customer.toString());
        if ("123".equals(customer.getId())) {
            throw new RuntimeException("There is something invalid in this object");
        }
    }


    public List<Customer> generateCustomer(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(value -> Customer.builder()
                        .id(UUID.randomUUID().toString())
                        .birthDate(LocalDate.now().minusDays(value))
                        .name(RandomStringUtils.randomAlphanumeric(10))
                        .build())
                .collect(Collectors.toList());
    }

    public void startProcess(int quantity) {
        generateCustomer(quantity).forEach(customerProducer::produce);
    }
}
