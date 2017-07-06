package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.exception.InvalidPasswordException;
import com.greenfox.helpmanager.login.exception.NoSuchAccountException;
import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import com.greenfox.helpmanager.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  AuthService authService;
  AccountRepository accountRepository;

  @Autowired
  public UserController(AuthService authService, AccountRepository accountRepository) {
    this.authService = authService;
    this.accountRepository = accountRepository;
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

  @PostMapping(value = "/register")
  public String register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
    if (authService.isRegisteredUser(username)) {
      return "register";
    } else {
      String pw_hashed = authService.hashPassword(password);
      accountRepository.save(new Account(username, pw_hashed));
      return "redirect:/login";
    }
  }

  @GetMapping(value = {"/login","/", ""})
  public String login() {
    return "login";
  }

  @GetMapping(value = {"/register"})
  public String register() {
    return "register";
  }

}
