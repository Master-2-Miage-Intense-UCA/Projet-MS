package com.course.client.beans;

public class OrderBean {
    private Long id;

    private Long cartId;

    public Double total;

    public Boolean checked;

    public OrderBean(Long id, Long cartId, Double total, Boolean checked) {
        this.id = id;
        this.cartId = cartId;
        this.total = total;
        this.checked = checked;
    }

    public OrderBean(){}

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
