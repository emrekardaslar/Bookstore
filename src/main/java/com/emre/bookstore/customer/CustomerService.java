package com.emre.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id " + id + " does not exists");
        }

        customerRepository.deleteById(id);
    }

    @Transactional
    public Optional<Customer> updateCustomer(Long customerId, Customer newCustomer) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("book with id " + " does not exists");
        }
        newCustomer.setId(customerId);
        customerRepository.save(newCustomer);
        return customerRepository.findById(customerId);
    }
}
