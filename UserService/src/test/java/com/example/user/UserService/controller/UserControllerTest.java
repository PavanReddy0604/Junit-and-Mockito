package com.example.user.UserService.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.user.UserService.Entitiy.User;
import com.example.user.UserService.service.UserService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserController.class})
class UserControllerTest {

	@Autowired
	UserController userController;
	
	@MockBean
	UserService userService;
	
	User user;
	User user2;
	List<User> usersList;
	ResponseEntity<User> resUser;
	User nullUser;

	private User user3;
	@BeforeEach
	void setUp()
	{
		user=new User();
		user.setId(2);
		user.setName("jai hanuman");
		user.setMobileNumber(988766655L);
		user2=new User();
		user2.setId(2);
		user2.setName("jai bajarang");
		user2.setMobileNumber(988766655L);
		usersList=new ArrayList<>();
		usersList.add(user);
		usersList.add(user2);
		user3 = new User();
		
	}
	@Test
	void testGetUser() {
		resUser = new ResponseEntity<>(user, HttpStatus.OK);
		when(userService.getUserById(Mockito.anyInt())).thenReturn(user);
		assertThat(userController.getUser(Mockito.anyInt())).isEqualTo(resUser);
		assertEquals(HttpStatus.OK, resUser.getStatusCode());
		assertEquals(user,resUser.getBody());
	}
	
	@Test
	void NullGetUserTest()
	{
		nullUser= null;
		ResponseEntity<User> nRes=new ResponseEntity<>(nullUser,HttpStatus.BAD_REQUEST);
		when(userService.getUserById(Mockito.anyInt())).thenReturn(nullUser);
		assertThat(userController.getUser(Mockito.anyInt())).isEqualTo(nRes);

		assertEquals(HttpStatus.BAD_REQUEST, nRes.getStatusCode());
//		assertEqual()
		
	}

	@Test
	void testGetAllUsers() {
		when(userService.getAllUsers()).thenReturn(usersList);
		ResponseEntity<List<User>> resList=new ResponseEntity<>(usersList,HttpStatus.OK);
		assertThat(userController.getAllUsers()).isEqualTo(resList);
		assertEquals(HttpStatus.OK, resList.getStatusCode());
		assertEquals(usersList, resList.getBody());
	}

	@Test
	void testSaveUser() {
		
		ResponseEntity<User> rs=resUser = new ResponseEntity<>(user2, HttpStatus.CREATED);
		when(userService.saveUser(user2)).thenReturn(user2);
		assertThat(userController.saveUser(user2)).isEqualTo(rs);
		assertEquals(HttpStatus.CREATED, rs.getStatusCode());
	}
	
	@Test
	void testDeleteUser() {
	when(userService.deleteUser(Mockito.anyInt())).thenReturn(2);
	//verify(userController,atLeastOnce()).deleteUser(Mockito.anyInt());
	assertThat(userController.deleteUser(Mockito.anyInt())).isEqualTo(2);
	}

}
