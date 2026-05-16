package com.bancaria.api.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record DataAddressCreate(
        @NotBlank String logradouro,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String uf,
        String complemento,
        @NotBlank String numero,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos") String cep
) {}