package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}/orders")
    public String userOrders(@PathVariable String id, Model model){
        List<Order> orders = orderRepository.findByUser_IdIgnoreCase(id);
        model.addAttribute("orders",orders);
        return "user/orders_list";
    }



}
