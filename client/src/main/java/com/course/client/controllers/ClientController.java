package com.course.client.controllers;

import com.course.client.beans.CartBean;
import com.course.client.beans.CartItemBean;
import com.course.client.beans.OrderBean;
import com.course.client.beans.ProductBean;
import com.course.client.proxies.MsOrderCart;
import com.course.client.proxies.MsProductProxy;
import com.course.client.proxies.MsCartProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class ClientController {

    @Autowired
    private MsProductProxy msProductProxy;
    @Autowired
    private MsCartProxy msCartProxy;
    @Autowired
    private MsOrderCart msOrderProxy;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products =  msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") long id, Model model) {
        Optional<ProductBean> product =  msProductProxy.get(id);
        model.addAttribute("product", product.get());
        return "product-detail";
    }

    @RequestMapping("/cart")
    public String cart(Model model) {
        Optional<CartBean> cartBean =  msCartProxy.getCart(1L);
        model.addAttribute("cart", cartBean.get());
        return "cart";
    }

    @RequestMapping("/cart/{id}")
    public String addCart(@PathVariable("id") long id,Model model) {
        CartItemBean cartItem = new CartItemBean();
        cartItem.setProductId(id);
        cartItem.setQuantity(1);
        msCartProxy.addProductToCart(1L, cartItem);

        List<ProductBean> products =  msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/order")
    public String order(Model model) {
        CartBean cart =  msCartProxy.getCart(1L).get();
        OrderBean order =  msOrderProxy.createNewOrder().getBody();

        List<CartItemBean> items = cart.getProducts();
        AtomicReference<Double> total = new AtomicReference<>(0D);
        items.forEach((i) -> {
            ProductBean product =  msProductProxy.get(i.getProductId()).get();
            total.updateAndGet(v -> v + product.getPrice());
        });
        order.setTotal(total.get());
        model.addAttribute("order", order);
        return "order";
    }

    @RequestMapping("/order/{id}")
    public String makeOrder(@PathVariable("id") long id, Model model) {
        OrderBean order = msOrderProxy.makeOrder(id).getBody();
        CartBean cart =  msCartProxy.emptyCart(1L).getBody();

        List<ProductBean> products =  msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }
}
