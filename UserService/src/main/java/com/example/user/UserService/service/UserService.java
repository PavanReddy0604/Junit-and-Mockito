package com.example.user.UserService.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.user.UserService.DAO.UserRepo;
import com.example.user.UserService.Entitiy.User;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public User getUserById(int id)
	{
	Optional<User> u = Optional.ofNullable(new User());
	u=userRepo.findById(id);
	log.info("getting the user with user id {}",id);
	if(u.isPresent())
		return u.get();
	return new User();
	}
	
	public User saveUser(User  user)
	{
		//System.out.println("in service before saving==="+user);
		User u = userRepo.save(user);
	//	System.out.println("in service after saving"+user);
		return u;
	}
	
	public List<User> getAllUsers()
	{
//		return userRepo.findAll();
		return Collections.emptyList();
	}
	
	public User updateUser(User user,int id)
	{
		User u=userRepo.findById(id).get();
		u.setId(user.getId());
		u.setMobileNumber(user.getMobileNumber());
		u.setName(user.getName());
		return u;
	}
	
	public int deleteUser(int id)
	{
		//System.out.println("trying to delete");
		userRepo.deleteById(id);
		//System.out.println("deleted");
		return id;
	}

	public List<User> findAllUSersPaging(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<User> userList = userRepo.findAll(paging);
		if(userList.hasContent())
			return userList.getContent();
		return Collections.emptyList();
	}
}
