package com.icoderman.app.controller;

import com.icoderman.app.exceptions.DuplicateUserException;
import com.icoderman.app.exceptions.SpittleNotFoundException;
import com.icoderman.app.model.User;
import com.icoderman.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable("userId") long userId, Model model) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(user);
		return "home";
	}

	@RequestMapping(value = "/duplicate", method = RequestMethod.GET)
	public String saveUser(Model model) {
		// we can replace try catch block with handleDuplicateUser method
		//try {
		userRepository.save();
		return "redirect:/";
		/*
		} catch (DuplicateUserException e) {
	      return "error/duplicate";
	    }
	    */
	}

	/**
	 * This handler works only for current controller
	 * @return
	 */
	@ExceptionHandler(DuplicateUserException.class)
	public String handleDuplicateUser() {
		return "error/duplicate";
	}

	@RequestMapping(value = "/incorrect", method = RequestMethod.GET)
	public String updateUser(Model model) {
		userRepository.update();
		return "redirect:/";
	}


}
