package com.order.management;

import com.order.management.entities.Customer;
import com.order.management.entities.CustomerType;
import com.order.management.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CustomerServiceTest {
  @Autowired
  private CustomerService customerService;

  @Test
  public void testPromoteCustomer() {
    Customer customer = new Customer();
    customer.setOrdersCount(10);
    customerService.promoteCustomer(customer);
    assertEquals(CustomerType.GOLD, customer.getType());

    customer.setOrdersCount(20);
    customerService.promoteCustomer(customer);
    assertEquals(CustomerType.PLATINUM, customer.getType());
  }
}
