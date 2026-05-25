package com.rubish.ecommerce.service;

import com.rubish.ecommerce.dto.CustomerDto;
import com.rubish.ecommerce.model.Customer;
import com.rubish.ecommerce.repository.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public Customer createCustomer( CustomerDto customerDto) {
        Customer customer=new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setContact(customerDto.getContact());
        customer.setGender(customerDto.getGender());
        customer.setAddress(customerDto.getAddress());
        return customerRepo.save(customer);
    }

    public CustomerDto findByEmail(String email) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer != null) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setEmail(customer.getEmail());
            customerDto.setPassword(customer.getPassword());
            return customerDto;
        }
        return null;
    }



}
