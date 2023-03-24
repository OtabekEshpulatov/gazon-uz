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

    public void saveOrder(OrderCreateDTO dto, User user) {
        System.out.println("dto.getPitchId() = " + dto.getPitchId());
        Order order = Order.builder()
                .startTime(LocalDateTime.parse(dto.getOrderDatetime()))
                .orderStatus(OrderStatus.REQUESTED)
                .minutes(Integer.parseInt(dto.getDuration()))
                .pitch(pitchRepository.getPitch(dto.getPitchId()))
                .user(user).build();
        System.out.println("ordersout = " + order);
        orderDAO.save(order);
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
