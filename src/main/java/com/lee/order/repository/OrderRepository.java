package com.lee.order.repository;

import com.lee.order.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
}
