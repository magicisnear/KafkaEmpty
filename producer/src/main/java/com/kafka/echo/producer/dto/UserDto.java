package com.kafka.echo.producer.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String name;
    private Address address;
}

