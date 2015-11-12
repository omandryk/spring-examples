package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="/", method= RequestMethod.GET)
	public String testJSP() {
		return "jsp-sample";
	}

	@RequestMapping(value="/jstl", method= RequestMethod.GET)
	public String testJSTL() {
		return "jstl-sample";
	}
}
