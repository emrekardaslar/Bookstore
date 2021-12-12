package com.emre.bookstore.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//@Configuration
//public class CustomerConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
//        return args -> {
//            Customer customer1 = new Customer("customer1", "customer@gmail.com", LocalDate.of(1999, Month.MAY, 28));
//            Customer customer2 = new Customer("customer2", "customer2@gmail.com", LocalDate.of(1999, Month.MAY, 28));
//
//            customerRepository.saveAll(List.of(customer1, customer2));
//        };
//
//
//    }
//}
