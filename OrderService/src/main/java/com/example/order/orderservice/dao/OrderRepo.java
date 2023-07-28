package com.example.order.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.orderservice.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{
	
	

}
