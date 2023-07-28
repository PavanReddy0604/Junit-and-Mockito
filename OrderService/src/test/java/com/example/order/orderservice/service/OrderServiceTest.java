package com.example.order.orderservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.order.orderservice.dao.OrderRepo;
import com.example.order.orderservice.entity.Order;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OrderService.class})
class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
	private OrderRepo orderRepo;
	
	private Order order1;
	private Order order2;
	private List<Order> orderList=new ArrayList<>();
	private Optional<Order> order;
	
	@BeforeEach
	void setUp()
	{
		order1=new Order(1,LocalDate.now(),"staus","name");
		order2=new Order(2,LocalDate.now(),"status2","name2");
		order=Optional.of(order2);
		
	}

	@Test
	void testGetOrder() {
		when(orderRepo.findById(1)).thenReturn(Optional.of(order1));
		assertThat(orderService.getOrder(1)).isEqualTo(order1);
	}

	@Test
	void testGetAllOrders() {
		orderList.add(order1);
		orderList.add(order2);
		when(orderRepo.findAll()).thenReturn(orderList);
		assertThat(orderService.getAllOrders()).isEqualTo(orderList);
	}

	@Test
	void testSaveOrder() {
		when(orderRepo.save(order2)).thenReturn(order2);
		assertEquals(orderService.saveOrder(order2), order2);
	}

	@Test
	void testUpdateOrder() {
		when(orderRepo.findById(2)).thenReturn(Optional.of(order2));
		order2.setOrderName("updatedName");
		orderService.updateOrder(order2, 2);
		assertEquals(order2.getOrderName(),"updatedName");
	}

	@Test
	void testDeleteOrder() {
		assertThat(orderService.deleteOrder(2)).isEqualTo(2);
	}

}