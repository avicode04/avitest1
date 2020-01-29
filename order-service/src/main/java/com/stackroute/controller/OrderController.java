package com.stackroute.controller;

import com.stackroute.domain.Order;
import com.stackroute.exception.OrderAlreadyExistsException;
import com.stackroute.exception.OrderDoesNotExistException;
import com.stackroute.service.KafkaProducerService;
import com.stackroute.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController

/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping(value = "api/v1")

@CrossOrigin(origins = "*")
public class OrderController {

    private OrderService orderService;
    private ResponseEntity responseEntity;
    private KafkaProducerService kafkaProducerService;

    @Autowired
    public OrderController(OrderService orderService, KafkaProducerService kafkaProducerService) {
        this.orderService = orderService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("order")
    public ResponseEntity<?> getAllOrders() {
        responseEntity = new ResponseEntity<List<Order>>(orderService.getAllOrderDetails(), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable String orderId) throws OrderDoesNotExistException {
        responseEntity = new ResponseEntity<Order>(orderService.getOrderDetailsById(orderId), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("order/buyerEmail/{buyerEmail}")
    public ResponseEntity<?> getAllOrdersByBuyerEmail(@PathVariable String buyerEmail){
        responseEntity = new ResponseEntity<List<Order>>(orderService.getAllOrderDetailsByBuyerEmail(buyerEmail), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("order")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) throws OrderAlreadyExistsException {
        responseEntity = new ResponseEntity<Order>(orderService.saveOrderDetails(order), HttpStatus.CREATED);

        //Producing this order on the kafka topic "orderDetails" for other services to consume
        kafkaProducerService.sendOrderDetails(order);

        return responseEntity;
    }

    @DeleteMapping("order/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId) throws OrderDoesNotExistException {
        responseEntity = new ResponseEntity<Boolean>(orderService.deleteOrder(orderId), HttpStatus.OK);
        return responseEntity;
    }

//    @PutMapping("order")
//    public ResponseEntity<?> updateOrder(@RequestBody Order order) throws OrderDoesNotExistException {
//        responseEntity = new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.OK);
//        return responseEntity;
//    }

}