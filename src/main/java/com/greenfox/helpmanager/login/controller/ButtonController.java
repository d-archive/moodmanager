package com.greenfox.helpmanager.login.controller;

import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import com.greenfox.helpmanager.login.service.JwtService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    account.setRequest(new Date());
    accountRepository.save(account);
    return "redirect:/main/" + token + "/" + username;
  }

  @PostMapping("/end/{token}/{username}")
  public String end(@PathVariable String token, @PathVariable String username) {
    Account account = accountRepository.findAccountByUsername(username);
    if (account.getStatus().equals("process")) {
      account.setStop(new Date());
      account.setCount(account.getCount() + 1);
    }
    account.setStatus("none");
    account.setStart(null);
    accountRepository.save(account);
    return "redirect:/main/" + token + "/" + username;
  }

  @PostMapping("/start/{token}/{username}")
  public String start(@PathVariable String token, @PathVariable String username) {
    Account account = accountRepository.findAccountByUsername(username);
    if (account.getStatus().equals("wait")) {
      account.setStart(new Date());
    }
    account.setStatus("process");
    accountRepository.save(account);
    return "redirect:/main/" + token + "/" + username;
  }

  @GetMapping("/panic")
  public String panic() {
    return "redirect:http://www.puffgames.com/bubblewrap/";
  }
}
