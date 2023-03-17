package com.example.order.repositories;

import com.example.order.domain.MsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<MsOrder, Long> {
}
