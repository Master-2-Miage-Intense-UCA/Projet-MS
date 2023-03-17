package com.course.client.proxies;

import com.course.client.beans.OrderBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(name = "ms-order", url = "localhost:8080")
public interface MsOrderCart {
    @PostMapping(value = "/order")
    public ResponseEntity<OrderBean> createNewOrder();

    @GetMapping(value = "/order/{id}")
    public Optional<OrderBean> getOrder(@PathVariable Long id);

    @PostMapping(value = "/order/{id}")
    public ResponseEntity<OrderBean> makeOrder(@PathVariable Long id);
}
