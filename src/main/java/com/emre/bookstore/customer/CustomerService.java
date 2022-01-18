package com.emre.bookstore.customer;

import com.emre.bookstore.registration.token.ConfirmationToken;
import com.emre.bookstore.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return customerRepository.findCustomerByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Customer customer) {
        boolean exists = customerRepository
                .findCustomerByEmail(customer.getEmail())
                .isPresent();

        if (exists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        String token =UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                customer
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        //TODO: SEND EMAIL

        return token;
    }

    public int enableCustomer(String email) {
        return customerRepository.enableCustomer(email);
    }
}
