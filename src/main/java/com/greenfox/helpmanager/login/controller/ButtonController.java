package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import com.greenfox.helpmanager.login.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ButtonController {

  private AccountRepository accountRepository;

  @Autowired
  public ButtonController(JwtService jwtService, AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @PostMapping("/help/{token}/{username}")
  public String help(@PathVariable String token, @PathVariable String username) {
    Account account = accountRepository.findAccountByUsername(username);
    account.setStatus("wait");
    accountRepository.save(account);
    return "redirect:/main/" + token + "/" + username;
  }

  @PostMapping("/end/{token}/{username}")
  public String end(@PathVariable String token, @PathVariable String username) {
    Account account =
  }
}
