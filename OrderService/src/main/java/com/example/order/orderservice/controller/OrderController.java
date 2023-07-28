package com.example.order.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.orderservice.entity.Order;
import com.example.order.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") int id)
	{
		Order order = orderService.getOrder(id);
		
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrders()
	{
		List<Order> allOrders = orderService.getAllOrders();
		return ResponseEntity.ok(allOrders);
		
	}
	@PostMapping("/")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order)
	{
		Order savedOrder = orderService.saveOrder(order);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedOrder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteOrder(@PathVariable("id") int id)
	{
		orderService.deleteOrder(id);
		return ResponseEntity.status(HttpStatus.OK).body(id);
	}
	
	
	
	

}
