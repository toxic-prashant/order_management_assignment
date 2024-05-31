package com.order.management.services;

import com.order.management.entities.Customer;
import com.order.management.repo.CustomerRepository;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

  @Resource
  private CustomerRepository customerRepository;

  @Scheduled(cron = "0 * * * * ?")
  public void sendPromotionalEmails() {
    List<Customer> customers = customerRepository.findAll();
    for (Customer customer : customers) {
      if (customer.getOrdersCount() == 9 || customer.getOrdersCount() == 19) {
        sendMail(customer);
      }
    }
  }

  public void sendMail(Customer customer) {
    System.out.println("Sent mail to customer: "+customer.getName() + ". You have placed " + customer.getOrdersCount() + " orders with us. Buy one more and you will be promoted to the next level!");
  }
}
