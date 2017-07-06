package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.exception.InvalidPasswordException;
import com.greenfox.helpmanager.login.exception.NoSuchAccountException;
import com.greenfox.helpmanager.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  AuthService authService;

  @Autowired
  public UserController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(value = "/login")
  public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
    try {
      authService.authenticate(username, password);
      return "main";
    } catch (NoSuchAccountException |InvalidPasswordException e) {
      return "login";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @GetMapping(value = {"/login","/", ""})
  public String login () {
    return "login";
  }
}
