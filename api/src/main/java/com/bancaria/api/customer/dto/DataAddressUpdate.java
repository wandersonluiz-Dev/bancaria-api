package com.bancaria.api.customer.dto;

import jakarta.validation.constraints.Pattern;

public record DataAddressUpdate(String logradouro,
                                @Pattern(regexp = "\\d{8}", message = "cep deve conter 8 digitos")
                                String cep,
                                String uf,
                                String cidade,
                                String bairro,
                                String complemento,
                                String numero) {
}
