package org.top.springapiorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.top.springapiorder.db.OrderRepository;
import org.top.springapiorder.db.OrderTEntity;

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

    // CREATE
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

    // READ
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<OrderTEntity> getAllOrders() {
        return orderRepository.findAll();
    }


    // UPDATE
    @PutMapping(path = "/update")
    public @ResponseBody String updateOrder(@RequestBody OrderTEntity orderTEntity) {
        Optional<OrderTEntity> orderDb = orderRepository.findById(orderTEntity.getIdF());
        if (orderDb.isPresent()) {
            OrderTEntity order = orderDb.get();
            if (!orderTEntity.getNameF().isEmpty())
                order.setNameF(orderTEntity.getNameF());
            if (!orderTEntity.getEmailF().isEmpty())
                order.setEmailF(orderTEntity.getEmailF());
            if (!orderTEntity.getPhoneF().isEmpty())
                order.setPhoneF(orderTEntity.getPhoneF());
            if (!orderTEntity.getAddressF().isEmpty())
                order.setAddressF(orderTEntity.getAddressF());

            orderRepository.save(order);
        }
        return "Update";
    }


    // DELETE
    @PostMapping(path = "/remove")
    public @ResponseBody String removeOrder(@RequestParam Integer id) {
        Optional<OrderTEntity> deleted = orderRepository.findById(id);
        if (deleted.isPresent()) {
            orderRepository.delete(deleted.get());
            return "Order " + deleted.get() + " deleted";
        }
        return "Order with id " + id + " is not exists";
    }

}
