package com.order.management;

import com.order.management.entities.Customer;
import com.order.management.entities.Order;
import com.order.management.services.CustomerService;
import com.order.management.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private CustomerService customerService;

  @Test
  public void testCreateOrder() {
    Customer customer = new Customer();
    customer = customerService.saveCustomer(customer);
    Order order = orderService.createOrder(customer.getId(), 100);
    assertEquals(0.0, order.getDiscount());

    customer.setOrdersCount(10);
    customerService.promoteCustomer(customer);
    order = orderService.createOrder(customer.getId(), 100);
    assertEquals(10.0, order.getDiscount());
  }
}
