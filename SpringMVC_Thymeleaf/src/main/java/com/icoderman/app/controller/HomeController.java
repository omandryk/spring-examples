package com.icoderman.app.controller;

import com.icoderman.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  public HomeController(RootConfig c) {

  }

  @Autowired
  public HomeController(RootConfig c, int i) {

  }

  @RequestMapping(method = GET)
  public String home(Model model) {
    return "home";
  }

}
