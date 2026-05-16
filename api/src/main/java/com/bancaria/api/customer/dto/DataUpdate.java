package com.bancaria.api.customer.dto;

public record DataUpdate(String name,
    String email,
    String phone,
    DataAddressUpdate address) {
}
