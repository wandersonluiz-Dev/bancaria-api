package com.bancaria.api.customer;

import com.bancaria.api.customer.dto.CustomerResponse;
import com.bancaria.api.customer.dto.RegistrationData;
import com.bancaria.api.customer.dto.DataUpdate;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse registerCustomer(@RequestBody @Valid RegistrationData data) {
       var customer = repository.save(new Customer(data));
        return new CustomerResponse(customer);
    }

    @GetMapping
    public List<CustomerResponse> listCustomers() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(CustomerResponse::new)
                .toList();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomerResponse> updateCustomers(@PathVariable Long id, @RequestBody @Valid DataUpdate data ) {
        var customer = repository.getReferenceById(id);
        customer.updateInformation(data);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var customers = repository.getReferenceById(id);
        repository.delete(customers);
    }
}
