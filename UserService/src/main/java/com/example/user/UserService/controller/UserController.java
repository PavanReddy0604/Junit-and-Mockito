package com.example.user.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.UserService.Entitiy.User;
import com.example.user.UserService.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id)
	{
		User u=userService.getUserById(id);
		
		if(u==null)
		{
			
			System.out.println("not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok(u);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		
		System.out.println("in controller before saving");
		User userr=userService.saveUser(user);
		System.out.println("in controller after saving");
		return ResponseEntity.status(HttpStatus.CREATED).body(userr);
	}
	
	@DeleteMapping("/user/{id}")
	public int deleteUser(@PathVariable("id") int id)
	{
		System.out.println("deleting");
		int deleteUser = userService.deleteUser(id);
		System.out.println("deleted");
		return deleteUser;
	}
	
	@GetMapping("/get")
	public List<User> getAllUsers( @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
            	return userService.findAllUSersPaging(pageNo, pageSize);
	}
	}
	
