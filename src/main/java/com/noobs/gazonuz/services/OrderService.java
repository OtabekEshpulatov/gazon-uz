package com.noobs.gazonuz.services;

import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.OrderRepository;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.OrderCreateDTO;
import com.noobs.gazonuz.enums.OrderStatus;
import com.noobs.gazonuz.repositories.OrderDAO;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDAO orderDAO;
    private final PitchRepository pitchRepository;

    public List<Order> findOrdersByUserId(String userId){
        return orderRepository.findOrdersByUserId(userId);
    }

    public void saveOrder(OrderCreateDTO dto, User user) {
        System.out.println("dto.getPitchId() = " + dto.getPitchId());
        Order order = Order.builder()
                .startTime(LocalDateTime.parse(dto.getOrderDatetime()))
                .orderStatus(OrderStatus.REQUESTED)
                .minutes(Integer.parseInt(dto.getDuration()))
                .isDeleted(false)
                .pitch(pitchRepository.getPitch(dto.getPitchId()))
                .user(user).build();
        System.out.println("ordersout = " + order);
        orderDAO.save(order);
    }
}
