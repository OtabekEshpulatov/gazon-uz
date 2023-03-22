package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}/orders")
    public String userOrders(@PathVariable String id, Model model){
        //connection to OrderService
        List<Order> orders = orderService.findOrdersByUserId(id);
        //end

        //model prep
        model.addAttribute("orders",orders);
        //end
        return "user/orders_list";
    }



}
