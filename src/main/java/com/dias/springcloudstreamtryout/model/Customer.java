package com.dias.springcloudstreamtryout.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Customer {
    private String id;
    private String name;
    private LocalDate birthDate;
}
