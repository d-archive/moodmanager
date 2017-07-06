package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.repository.AccountRepository;
import com.greenfox.helpmanager.login.service.AuthService;
import com.greenfox.helpmanager.login.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

  private JwtService jwtService;
  private AccountRepository accountRepository;

  @Autowired
  public MainController(JwtService jwtService, AccountRepository accountRepository) {
    this.jwtService = jwtService;
    this.accountRepository = accountRepository;
  }

  @GetMapping("/main/{token}/{username}")
  public String getMain(@PathVariable String token, @PathVariable String username, Model model) throws Exception {
//    System.out.println(accountRepository.findAccountByUsername(username).getStatus());
    System.out.println(accountRepository.findAccountByUsername(username).getName());
    System.out.println(accountRepository.findAccountByUsername(username));
    try {
      if (jwtService.isValid(username, token)) {
        model.addAttribute("singleaccount", accountRepository.findAccountByUsername(username));
        model.addAttribute("accounts", accountRepository.findAll());
        return "main";
      } else {
        return "redirect:/login";
      }
    } catch (Exception e) {
      return "redirect:/login";
    }
  }
}
