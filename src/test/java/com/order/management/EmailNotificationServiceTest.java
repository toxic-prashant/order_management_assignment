package com.order.management;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.order.management.entities.Customer;
import com.order.management.repo.CustomerRepository;
import com.order.management.services.EmailNotificationService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailNotificationServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private EmailNotificationService emailNotificationService;

  @Test
  public void testSendPromotionEmails() {
    MockitoAnnotations.openMocks(this);

    Customer customer1 = new Customer();
    customer1.setName("Bavita Varshney");
    customer1.setOrdersCount(9);

    Customer customer2 = new Customer();
    customer2.setName("Prashant Yadav");
    customer2.setOrdersCount(19);

    when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
    emailNotificationService.sendPromotionalEmails();

    verify(customerRepository, times(1)).findAll();
  }
}
