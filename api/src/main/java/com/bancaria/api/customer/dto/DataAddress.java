package com.bancaria.api.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAddress(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank  String uf,
        @NotBlank
        String complemento,
        String numero,
        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "cep deve conter 8 digitos")
        String cep
        ) {
}
