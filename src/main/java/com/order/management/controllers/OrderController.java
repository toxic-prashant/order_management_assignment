package com.order.management.controllers;

import com.order.management.entities.Order;
import com.order.management.services.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Resource
  private OrderService orderService;

  @PostMapping
  public Order createOrder(@RequestParam Long customerId, @RequestParam double amount) {
    return orderService.createOrder(customerId, amount);
  }
}
