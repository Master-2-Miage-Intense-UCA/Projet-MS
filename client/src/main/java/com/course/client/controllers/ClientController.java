package com.course.client.controllers;

import com.course.client.beans.CartBean;
import com.course.client.beans.CartItemBean;
import com.course.client.beans.ProductBean;
import com.course.client.proxies.MsProductProxy;
import com.course.client.proxies.MsCartProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private MsProductProxy msProductProxy;
    @Autowired
    private MsCartProxy msCartProxy;

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
        Optional<CartBean> cart =  msCartProxy.getCart(1L);
        model.addAttribute("cart", cart.get());
        return "cart";
    }
}
