package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import com.greenfox.helpmanager.login.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MoodController {

  private AccountRepository accountRepository;

  @Autowired
  public MoodController(JwtService jwtService, AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("/mood/{token}/{username}/{mood}")
  public String mood(@PathVariable String token, @PathVariable String username, @PathVariable String mood) {
    Account account = accountRepository.findAccountByUsername(username);
    account.setMood(mood);
    accountRepository.save(account);
    return "redirect:/main/" + token + "/" + username;
  }
}
