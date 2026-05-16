package com.bancaria.api.customer.dto;

import com.bancaria.api.customer.Customer;

public record CustomerResponse(Long id,
                               String name,
                               String email,
                               String phone,
                               String cpf,
                               AddressResponse address) {

    public CustomerResponse(Customer customer) {
        this(customer.getId(),
             customer.getName(),
             customer.getEmail(),
             customer.getPhone(),
             customer.getCpf(),
             customer.getAddress() != null ? new AddressResponse(customer.getAddress()) : null);
    }
}
