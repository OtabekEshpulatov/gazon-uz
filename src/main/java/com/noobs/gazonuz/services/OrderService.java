package com.noobs.gazonuz.services;

import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findOrdersByUserId(String userId){
        return orderRepository.findOrdersByUserId(userId);
    }
}
