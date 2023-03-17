package com.example.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MsOrder {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    public Double total;

    public Boolean checked;

    public MsOrder() {
    }

    public MsOrder(Long id, Long cartId, Double total, Boolean checked){
        this.id = id;
        this.cartId = cartId;
        this.total = total;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public Long getCartId() {
        return cartId;
    }

    public Double getTotal() {
        return total;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
