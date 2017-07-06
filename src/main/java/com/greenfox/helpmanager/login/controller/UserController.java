package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.exception.InvalidPasswordException;
import com.greenfox.helpmanager.login.exception.NoSuchAccountException;
import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import com.greenfox.helpmanager.login.service.AuthService;
import com.greenfox.helpmanager.login.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  AuthService authService;
  AccountRepository accountRepository;
  JwtService jwtService;

  @Autowired
  public UserController(AuthService authService, AccountRepository accountRepository, JwtService jwtService) {
    this.authService = authService;
    this.accountRepository = accountRepository;
    this.jwtService = jwtService;
  }

  @PostMapping(value = "/login")
  public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
    try {
      authService.authenticate(username, password);
      String token = jwtService.createJwt("powerpuff", "user", 1800000L, username);
      authService.giveToken(username, token);
      return "redirect:/main/" + token + "/" + username;
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
