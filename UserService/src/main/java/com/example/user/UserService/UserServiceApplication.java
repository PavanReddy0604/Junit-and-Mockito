package com.example.user.UserService;

import java.util.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.user.UserService.Entitiy.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
//		String str="aback89389";
//        String encodeToString = Base64.getEncoder().encodeToString(str.getBytes());
//        System.out.println("encrypted "+ encodeToString);
//		
//		String token="token1";
//
//        String encode = Base64.getEncoder().encodeToString(token.getBytes());
//        System.out.println("encrpted "+encode);
//        
//        
        String a="hi";
        String b="hi";
        
        if(a==b)
        {
        	System.out.println("both are same");
        }
        else
        	System.out.println("not same");
        
        String aa=new String("hi");
        String bb=new String("Java");
        
        
        if(aa.equals(b))
        {
        	System.out.println("both are same...");
        }
        else
        	System.out.println("not same...");
//		long a = 67, b = 3;
//		int sign = (a < 0 && b > 0) || (a > 0 && b < 0) ? -1 : +1;
//
//		int ans = 0;
//		a = Math.abs(a);
//		b = Math.abs(b);
//		for (int i = 31; i >= 0; i--) {
//			if ((b << i) <= a) {
//				a = a - (b << i);
//				ans = ans | (1 << i);
//
//			}
//		}
//		System.out.println(ans * sign);

        User u=new User(12,"java",9328480L);
        ObjectMapper om = new ObjectMapper();
        try {
			String jsonString = om.writeValueAsString(u);
			 System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
	}

}
