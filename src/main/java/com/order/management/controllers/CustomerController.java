package com.order.management.controllers;

import com.order.management.entities.Customer;
import com.order.management.services.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  @Resource
  private CustomerService customerService;

  @PostMapping
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerService.saveCustomer(customer);
  }

  @GetMapping("/{id}")
  public Customer getCustomer(@PathVariable Long id) {
    return customerService.findCustomerById(id);
  }
}
