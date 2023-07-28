package com.example.user.UserService.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.example.user.UserService.DAO.UserRepo;
import com.example.user.UserService.Entitiy.User;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class})
class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepo userRepo;
	
	Optional<User> user;
	
	User saveUser;
	
	List<User> usersList;
	
	@BeforeEach
	void setUp()
	{
		user = Optional.ofNullable(new User());
		
		user.get().setId(1);
		user.get().setMobileNumber(979484848L);
		user.get().setName("jai shreeram");
		saveUser=new User();
		saveUser.setId(88);
		saveUser.setMobileNumber(1233L);
		saveUser.setName("jai hanuman");
		
		usersList=new ArrayList<>();
		usersList.add(saveUser);
		usersList.add(user.get());
	}

	@Test
	void testGetUserById() {
		when(userRepo.findById(Mockito.any())).thenReturn(user);
		assertThat(userService.getUserById(Mockito.anyInt())).isEqualTo(user.get());
	}
	
	@Test
	void testGetUserByIdNullCase() {
		when(userRepo.findById(Mockito.any())).thenReturn(Optional.empty());
		assertNotNull(userService.getUserById(Mockito.anyInt()));
	}

	@Test
	void testSaveUser() {
		when(userRepo.save(saveUser)).thenReturn(saveUser);
		assertThat(userService.saveUser(saveUser).getId()).isEqualTo(saveUser.getId());
	}

	@Test
	void testGetAllUsers() {
		when(userRepo.findAll()).thenReturn(usersList);
		assertThat(userService.getAllUsers()).isEqualTo(usersList);
	}

	@Test
	void testUpdateUser() {
		when(userRepo.findById(88)).thenReturn(Optional.of(saveUser));
//		verify(userService., atMostOnce()).updateUser(saveUser, 88);
		assertEquals(userService.updateUser(saveUser, 88), saveUser);
	}

	@Test
	void testDeleteUser() {
	int actualValue = userService.deleteUser(1);
	verify(userRepo,times(1)).deleteById(Mockito.anyInt());
	assertEquals(actualValue, 1);
	
	
//		int deletedUserId = userService.deleteUser(88);
//
//	    // Verify that the deleteById method was called with the correct argument
//	    verify(userRepo, times(1)).deleteById(88);
//
//	    // Assert that the returned user ID matches the input user ID
//	    assertEquals(88, deletedUserId);
	}

}
