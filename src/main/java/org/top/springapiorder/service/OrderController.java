package org.top.springapiorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.top.springapiorder.db.OrderRepository;
import org.top.springapiorder.db.OrderTEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/ping")
    public @ResponseBody String ping() {
        return "pong";
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewOrder(@RequestParam String name, @RequestParam String email
            , @RequestParam String phone, @RequestParam String address) {
        OrderTEntity order = new OrderTEntity();
        order.setNameF(name);
        order.setEmailF(email);
        order.setPhoneF(phone);
        order.setAddressF(address);
        orderRepository.save(order);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<OrderTEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping(path="/remove")
    public @ResponseBody String removeUser(@RequestParam Integer id) {
        Optional<OrderTEntity> deleted = orderRepository.findById(id);
        if (deleted.isPresent()) {
            orderRepository.delete(deleted.get());
            return "Order " + deleted.get() + " deleted";
        }
        return "Order with id " + id + " is not exists";
    }

}
