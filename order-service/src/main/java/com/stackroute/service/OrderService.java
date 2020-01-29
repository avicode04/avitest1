package com.stackroute.service;

import com.stackroute.domain.Order;
import com.stackroute.exception.OrderAlreadyExistsException;
import com.stackroute.exception.OrderDoesNotExistException;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrderDetails();

    public Order getOrderDetailsById(String  orderId) throws OrderDoesNotExistException;

    public Order saveOrderDetails(Order order) throws OrderAlreadyExistsException;

    public boolean deleteOrder(String orderId) throws OrderDoesNotExistException;

   public Order updateOrder(Order order) throws OrderDoesNotExistException;

    public List<Order> getAllOrderDetailsByBuyerEmail(String buyerEmail);

}
