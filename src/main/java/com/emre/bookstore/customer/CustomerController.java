package com.emre.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return this.customerService.getCustomers();
    }

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestBody Customer newCustomer) {

        return new ResponseEntity<>(customerService.updateCustomer(customerId, newCustomer), HttpStatus.OK);
    }

}
