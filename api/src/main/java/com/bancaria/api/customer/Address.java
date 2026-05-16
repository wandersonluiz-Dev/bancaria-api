package com.bancaria.api.customer;

import com.bancaria.api.customer.dto.DataAddress;
import com.bancaria.api.customer.dto.DataAddressUpdate;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String uf;

    public Address(DataAddress data) {
        this.logradouro = data.logradouro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.cep = data.cep();
        this.uf = data.uf();
    }

    public void updateAddress(@Valid DataAddressUpdate data) {
        if (data.logradouro() != null) {
            this.logradouro = data.logradouro();
        }
        if (data.numero() != null) {
            this.numero = data.numero();
        }
        if (data.complemento() != null) {
            this.complemento = data.complemento();
        }
        if (data.bairro() != null) {
            this.bairro = data.bairro();
        }
        if (data.cidade() != null) {
            this.cidade = data.cidade();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }

    }

}
