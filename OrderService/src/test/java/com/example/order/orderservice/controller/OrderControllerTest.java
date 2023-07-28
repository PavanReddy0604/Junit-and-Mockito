package com.example.order.orderservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.order.orderservice.entity.Order;
import com.example.order.orderservice.service.OrderService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OrderController.class})
class OrderControllerTest {

	@Autowired
	private OrderController orderController;
	
	@MockBean
	private OrderService orderService;
	
	Order order1;
	Order order2;
	
	List<Order> orderList;
	ResponseEntity<Order> resOrder;
	ResponseEntity<List<Order>> resOrderList;
	ResponseEntity<Integer> resInteger;
	@BeforeEach
	void setUp()
	{
		order1=new Order(1,LocalDate.now(),"name1","status1");
		order2=new Order(2,LocalDate.now(),"name2","status2");
	      orderList=new ArrayList<>();
	}
	
	
	@Test
	void testGetOrderById() {
		resOrder = new ResponseEntity<>(order1,HttpStatus.OK);
		when(orderService.getOrder(1)).thenReturn(order1);
		assertThat(orderController.getOrderById(1)).isEqualTo(resOrder);
		assertEquals(HttpStatus.OK, resOrder.getStatusCode());
		assertEquals(order1, resOrder.getBody());
	}

	@Test
	void testGetAllOrders() {
		resOrderList = new ResponseEntity<>(orderList,HttpStatus.OK);
		 when(orderService.getAllOrders()).thenReturn(orderList);
		 assertThat(orderController.getAllOrders()).isEqualTo(resOrderList);
		 assertEquals(HttpStatus.OK, resOrderList.getStatusCode());
		 assertEquals(orderList,resOrderList.getBody());
	}

	@Test
	void testSaveOrder() {
		when(orderService.saveOrder(order2)).thenReturn(order2);
		resOrder = new ResponseEntity<>(order2,HttpStatus.ACCEPTED);
		assertThat(orderController.saveOrder(order2)).isEqualTo(resOrder);
		assertEquals(HttpStatus.ACCEPTED, resOrder.getStatusCode());
		assertEquals(order2, resOrder.getBody());
		
	}

	@Test
	void testDeleteOrder() {
		orderService.deleteOrder(2);
		resInteger=new ResponseEntity<Integer>(order2.getOrderId(),HttpStatus.OK);
		assertThat(orderController.deleteOrder(2)).isEqualTo(resInteger);
		assertEquals(HttpStatus.OK, resInteger.getStatusCode());
		assertEquals(order2.getOrderId(), resInteger.getBody());
	}

}
