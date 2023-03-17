package com.example.order.controller;

import com.example.order.domain.MsOrder;
import com.example.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;


    @PostMapping(value = "/order")
    public ResponseEntity<MsOrder> createNewOrder()
    {
        long countOrder = orderRepository.count();
        MsOrder o = new MsOrder();
        if (countOrder != 0){
            o = orderRepository.findById(countOrder).get();
            if(o.checked){
                o = new MsOrder();
                o.setCartId(1L);
                o.setTotal(0D);
                o.setChecked(false);
                o = orderRepository.save(o);
            }}
        else {
            o.setCartId(1L);
            o.setTotal(0D);
            o.setChecked(false);
            o = orderRepository.save(o);
        }

        if (o == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");
        return new ResponseEntity<>(o, HttpStatus.CREATED);

    }

    @GetMapping(value = "/order/{id}")
    public Optional<MsOrder> getOrder(@PathVariable Long id)
    {
        Optional<MsOrder> order = orderRepository.findById(id);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get order");
        return order;
    }

    @PostMapping(value = "/order/{id}")
    public ResponseEntity<MsOrder> makeOrder(@PathVariable Long id){
        MsOrder order = orderRepository.findById(id).get();
        order.checked = true;
        order = orderRepository.save(order);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");
        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }

}
