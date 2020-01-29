package com.stackroute.service;

import com.stackroute.domain.Order;
import com.stackroute.exception.OrderAlreadyExistsException;
import com.stackroute.exception.OrderDoesNotExistException;
import com.stackroute.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    //private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrderDetails() {
        List<Order> fetchedOrderList;
        fetchedOrderList = orderRepository.findAll();
        return fetchedOrderList;
    }

    @Override
    public Order getOrderDetailsById(String orderId) throws OrderDoesNotExistException {
        Order fetchedOrder;
        if(!orderRepository.findById(orderId).isPresent()){
            throw new OrderDoesNotExistException("Order Does Not Exist");
        }
        else {
            fetchedOrder = orderRepository.findById(orderId).get();
        }
        return fetchedOrder;
    }

    @Override
    public Order saveOrderDetails(Order order) throws OrderAlreadyExistsException {

        /*if(orderRepository.findById(order.getOrderId()).isPresent()){
            throw new OrderAlreadyExistsException("Order Already Exists");
        }
        else {*/
       // }
//        }
        order.setTimestamp(new Date());
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public boolean deleteOrder(String orderId) throws OrderDoesNotExistException {
        if(!orderRepository.findById(orderId).isPresent()){
            throw new OrderDoesNotExistException("Order Does Not Exist");
        }
        else {
            orderRepository.deleteById(orderId);
        }
        return true;
    }

    @Override
    public Order updateOrder(Order order) throws OrderDoesNotExistException {
        Order updatedOrder = new Order();
        if(!orderRepository.findById(order.getOrderId()).isPresent()){
            throw new OrderDoesNotExistException("Order Does Not Exist");
        }
        else {
            updatedOrder.setOrderId(order.getOrderId());
            updatedOrder.setBuyerEmail(order.getBuyerEmail());
            updatedOrder.setRating(order.getRating());
            updatedOrder.setProducts(order.getProducts());
            updatedOrder.setTimestamp(order.getTimestamp());
            updatedOrder.setFinishTimestamp(order.getFinishTimestamp());
            updatedOrder.setStatus(order.getStatus());
            updatedOrder.setBuyerHomeAddress(order.getBuyerHomeAddress());
            updatedOrder.setBuyerOfficeAddress(order.getBuyerOfficeAddress());
            orderRepository.save(updatedOrder);
        }
        return updatedOrder;
    }

    @Override
    public List<Order> getAllOrderDetailsByBuyerEmail(String buyerEmail) {
        List<Order> fetchedOrderListByBuyerEmail;
        fetchedOrderListByBuyerEmail = orderRepository.findByBuyerEmail(buyerEmail);
        return fetchedOrderListByBuyerEmail;
    }

    @KafkaListener(topics = "orderRefined", groupId = "order-id",containerFactory = "kafkaListenerContainerFactory")
    public void consumeSeller(@Payload Order order) throws OrderDoesNotExistException, OrderAlreadyExistsException {
        System.out.println(order.toString());
        if(!orderRepository.findById(order.getOrderId()).isPresent()){
            this.saveOrderDetails(order);
        }
        else {
            this.updateOrder(order);
        }
    }
}
