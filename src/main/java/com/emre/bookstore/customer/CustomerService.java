package com.emre.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void addCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id " + id + " does not exists");
        }

        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomer(Long customerId, String username, String email, String dob) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("customer with id " + customerId + "does not exists" ));

        if (username != null && username.length() > 0
        && !Objects.equals(customer.getUsername(), username)) {
            customer.setUsername(username);
        }

        if (email != null && email.length() > 0
        && !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);

            if (customerOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            customer.setEmail(email);
        }

        if (dob != null && !Objects.equals(customer.getDob(), LocalDate.parse(dob))) {
            customer.setDob(LocalDate.parse(dob));
        }
    }
}
