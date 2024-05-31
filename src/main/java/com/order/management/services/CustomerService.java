package com.order.management.services;

import com.order.management.entities.Customer;
import com.order.management.entities.CustomerType;
import com.order.management.repo.CustomerRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Resource
  private CustomerRepository customerRepository;

  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer findCustomerById(Long id) {
    return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!!"));
  }

  public void promoteCustomer(Customer customer) {
    if (customer.getOrdersCount() >= 20) {
      customer.setType(CustomerType.PLATINUM);
    } else if (customer.getOrdersCount() >= 10) {
      customer.setType(CustomerType.GOLD);
    } else {
      customer.setType(CustomerType.REGULAR);
    }
    customerRepository.save(customer);
  }

}
