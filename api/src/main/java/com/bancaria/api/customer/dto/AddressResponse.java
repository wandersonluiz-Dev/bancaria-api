package com.bancaria.api.customer.dto;

import com.bancaria.api.customer.Address;

public record AddressResponse(String logradouro, String complemento, String bairro, String cidade, String cep, String uf) {

    public AddressResponse(Address address) {
        this(address.getLogradouro(),
                address.getComplemento(),
                address.getBairro(),
                address.getCidade(),
                address.getCep(),
                address.getUf());
    }

}
