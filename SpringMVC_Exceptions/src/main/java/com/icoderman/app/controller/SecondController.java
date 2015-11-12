package com.icoderman.app.controller;

import com.icoderman.app.model.User;
import com.icoderman.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecondController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/incorrect2", method = RequestMethod.GET)
	public String updateUser(Model model) {
		userRepository.update();
		return "redirect:/";
	}

	/**
	 * Redirecting with URL templates
	 *
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createUser(User user, Model model) {
		//userRepository.create();
		String userName = "bobo"; //user.getUsername();
		model.addAttribute("username", userName);
		model.addAttribute("id", 123);
		// username will be escaped, id will be added as query parameter
		return "redirect:/user/{username}";
	}

	@RequestMapping(value = "/user/bobo", method = RequestMethod.GET)
	public String showUser(Model model) {
		//User user = userRepository.findByUsername(username);
		//model.addAttribute();
		return "profile";
	}

	/**
	 * newUser will be added to the session and extracted in showUserWithFlash
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create_flash", method = RequestMethod.GET)
	public String createUserFlashRedirect(User user, RedirectAttributes model) {
		//userRepository.create();
		User newUser = new User("lolo","Tom", "Cat", 10);
		model.addAttribute("username", newUser.getUsername());
		model.addFlashAttribute("user", newUser);
		return "redirect:/user/{username}";
	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public String showUserWithFlash(@PathVariable String username, Model model) {
		if (!model.containsAttribute("user")) {
			//model.addAttribute(userRepository.findByUsername(username));
		}
		System.out.println(model.asMap());
		return "profile";
	}
}
