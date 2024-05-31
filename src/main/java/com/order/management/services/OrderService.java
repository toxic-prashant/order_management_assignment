package com.order.management.services;

import com.order.management.entities.Customer;
import com.order.management.entities.Order;
import com.order.management.repo.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Resource
  private OrderRepository orderRepository;

  @Resource
  private CustomerService customerService;

  @Resource
  private EmailNotificationService emailNotificationService;

  public Order createOrder(Long customerId, double amount) {
    Customer customer = customerService.findCustomerById(customerId);
    double discount = calculateDiscount(customer, amount);
    Order order = new Order();
    order.setCustomer(customer);
    order.setAmount(amount);
    order.setDiscount(discount);

    Order saveOrder = orderRepository.save(order);

    customer.setOrdersCount(customer.getOrdersCount()+1);
    customerService.promoteCustomer(customer);

    //Check the promotion condition and sends email, or we can say that it's an optional approach for sending email based on the orders count
//    if (customer.getOrdersCount() == 9 || customer.getOrdersCount() == 19) {
//      emailNotificationService.sendMail(customer);
//    }

    return saveOrder;
  }

  private double calculateDiscount(Customer customer, double amount) {
    switch (customer.getType()) {
      case GOLD: return amount * 0.10;
      case PLATINUM: return amount * 0.20;
      default: return 0.0;
    }
  }

}
