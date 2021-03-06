package com.login.springboot.web.login.hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.springboot.web.login.hello.User;
import com.login.springboot.web.login.hello.UserRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
	
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@RequestMapping(path="/add") 
	public @ResponseBody void addNewUser (@RequestParam String name
			, @RequestParam String pass) {


		User n = new User();
		n.setusername(name);
		n.setIs_admin(false);
		n.setpassword(pass);
		userRepository.save(n);
	
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public @ResponseBody User showUser(@RequestParam String name,@RequestParam String pass){
		User user = userRepository.findByUsernameAndPassword(name,pass);
        return user;
		
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public @ResponseBody Optional<User> showUser(@RequestParam Integer id){
	    return userRepository.findById(id);
	}
	
	@RequestMapping(value="/del_id", method = RequestMethod.GET)
	public @ResponseBody void delUser(@RequestParam Integer id){
	   userRepository.deleteById(id);
	}
	
	@RequestMapping(value="/finduname", method = RequestMethod.GET)
	public @ResponseBody User showUsername(@RequestParam String name){
		User user = userRepository.findByUsername(name);
        return user;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public @ResponseBody void updateUser(@RequestParam String name,@RequestParam String password,@RequestParam String yeni){
		User user=userRepository.findByUsername(name);
		user.setpassword(password);
		user.setusername(yeni);
		userRepository.save(user);
		
       
	}

	
	/*
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public @ResponseBody void deleteUser(){
	    userRepository.deleteAll();
	}*/
}