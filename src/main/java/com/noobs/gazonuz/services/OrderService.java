package com.noobs.gazonuz.services;

import com.noobs.gazonuz.configs.properties.ApplicationProperties;
import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.OrderRepository;
import com.noobs.gazonuz.dtos.OrderDto;
import com.noobs.gazonuz.enums.OrderStatus;
import com.noobs.gazonuz.exceptions.OrderNotFoundException;
import com.noobs.gazonuz.mappers.OrderMapper;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EmailService emailService;
    private final ApplicationProperties applicationProperties;
    private final AuthUserRepository authUserRepository;
    private final Utils utils;

    public List<Order> findOrdersByUserId(String userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    public List<OrderDto> getAllRequestedPitches(String pitchId) {


        final List<Order> order = orderRepository.findByStatusAndId(pitchId , OrderStatus.REQUESTED);
//        final List<String> usernamesByIds = authUserRepository.findUsernamesByIds(order.stream().map(Order::getUserId).toList());
        List<OrderDto> orderDtos = new ArrayList<>();
        for ( final Order innerOrder : order ) {
            orderDtos.add(orderMapper.toOrderStatus(innerOrder , authUserRepository.findEmailByID(innerOrder.getUserId())));
        }
        return orderDtos;


    }

    public void rejectOrder(String orderId) {


        final Properties properties = applicationProperties.getProperties();
        orderRepository.findById(orderId).ifPresentOrElse(order -> {

            final String email = authUserRepository.findEmailByID(order.getUserId());
            orderRepository.updateOrderStatusById(OrderStatus.NOT_ACCEPTED , orderId);
            emailService.sendMessageToEmailThroughSMTP(email , properties.getProperty("pitch.order.not.accepted.body").formatted(utils.formatDate(order.getStartTime())) , properties.getProperty("pitch.order.not.accepted.header"));
        } , () -> {
            throw new OrderNotFoundException("Cannot find order with this id");
        });


    }

    public void acceptOrder(String orderId) {

        final Properties properties = applicationProperties.getProperties();
        orderRepository.findById(orderId).ifPresentOrElse(order -> {
            final String email = authUserRepository.findEmailByID(order.getUserId());
            orderRepository.updateOrderStatusById(OrderStatus.ACCEPTED , orderId);
            emailService.sendMessageToEmailThroughSMTP(email , properties.getProperty("pitch.order.accepted.body").formatted(utils.formatDate(order.getStartTime())) , properties.getProperty("pitch.order.accepted.header"));
        } , () -> {
            throw new OrderNotFoundException("Cannot find order with this id");
        });


    }
}
