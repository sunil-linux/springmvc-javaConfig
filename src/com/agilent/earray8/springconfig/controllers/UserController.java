package com.agilent.earray8.springconfig.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agilent.earray8.springconfig.beans.User;
import com.agilent.earray8.springconfig.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
   
   @RequestMapping(value="/header",method=RequestMethod.GET)
   public String header() {      
        return "header";
   }

   @RequestMapping(value="/footer",method=RequestMethod.GET)
   public String footer() {      
        return "footer";
   }

   
    @RequestMapping(value = "/get", method = RequestMethod.GET)  
    public @ResponseBody User getUser(@RequestParam("id") long id) {        
        return userService.getUserById(id);
    }
  
    // For add & update the user 
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveUser( @ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
    	    
    	if(result.hasErrors()) {    		
    		FieldError error = result.getFieldError();
    		log.info(error.getDefaultMessage());
    		return "redirect:listUsers";
    		
    	}else {
   	
	    	if(user.getId() == 0){
	    		//new person, add it.
	    		userService.saveUser(user);
	    	}
	    	else {
	    		//existing person, update it.
	    		userService.updateUser(user);
	    	}
	    	return "redirect:listUsers";
    	}
    }
    
    @RequestMapping(value="/listUsers",method=RequestMethod.GET)
	public String getAllUsers(Model model){		
		model.addAttribute("user", new User());
		model.addAttribute("usersList", userService.getAllUsers());
		return "users";
	}
    
    
    @RequestMapping(value="/remove/{id}",method=RequestMethod.GET)
	public String removeUser(@PathVariable("id") long id){				
    	userService.deleteUser(new User(id));  	
		return "redirect:/user/listUsers";
	}
    
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
   	public String modifyUser(@PathVariable("id") long id,Model model){	
    	model.addAttribute("user",userService.getUserById(id));
    	model.addAttribute("usersList", userService.getAllUsers());       	
   		return "users";
   	}
} 
