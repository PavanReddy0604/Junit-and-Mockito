package com.example.user.UserService.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.user.UserService.Entitiy.User;

public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
	
	

}
