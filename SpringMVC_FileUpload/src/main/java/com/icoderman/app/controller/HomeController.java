package com.icoderman.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "/upload1", method = POST)
	public String upload1(@RequestPart("profilePicture") byte[] profilePicture) {
		System.out.println(profilePicture.length);
		return "home";
	}

	/**
	 * Instead of MultipartFile we can use javax.servlet.http.Part. In this case we don't have to
	 * to configure the StandardServlet- MultipartResolver bean. StandardServletMultipartResolver is
	 * required only when you’re working with MultipartFile.
	 *
	 * @param profilePicture
	 * @return
	 */
	@RequestMapping(value = "/upload2", method = POST)
	public String upload2(@RequestPart("profilePicture") MultipartFile profilePicture) {
		System.out.println(profilePicture.getOriginalFilename());
		return "home";
	}

}
