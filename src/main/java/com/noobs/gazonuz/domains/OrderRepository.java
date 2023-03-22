package com.noobs.gazonuz.domains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("select o from Order o where o.isDeleted=false and o.user.email=:id" )
    List<Order> findOrdersByUserId(String id);
}