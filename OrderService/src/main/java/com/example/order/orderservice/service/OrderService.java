package com.example.order.orderservice.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.orderservice.dao.OrderRepo;
import com.example.order.orderservice.entity.Order;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	
	public Order getOrder(int id)
	{
		Order order=orderRepo.findById(id).get();
		return order;
		
	}
	
	public List<Order> getAllOrders()
	{
		List<Order> allOrders = orderRepo.findAll();
		return allOrders;
	}
	
	public Order saveOrder(Order order)
	{
		order.setOrderDate(LocalDate.now());
		Order savedOrder = orderRepo.save(order);
		return savedOrder;
	}
	
	public void updateOrder(Order order , int id)
	{
		Order o = orderRepo.findById(id).get();
		o.setOrderName(order.getOrderName());
		o.setOrderStatus(order.getOrderStatus());
		
	}
	
	public int deleteOrder(int id)
	{
		orderRepo.deleteById(id);
		return id;
		
	}
}
