package com.example.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}