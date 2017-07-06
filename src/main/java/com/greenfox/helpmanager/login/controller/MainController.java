package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.service.AuthService;
import com.greenfox.helpmanager.login.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

  @Autowired JwtService jwtService;

  @GetMapping("/main/{token}/{username}")
  public String getMain(@PathVariable String token, @PathVariable String username, Model model) {
    if (jwtService.isValid(username, token)) {
      model.addAttribute(username);
      return "main";
    } else {
      return "redirect:/login";
    }
  }
}
