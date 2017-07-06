package com.greenfox.helpmanager.login.service;

import com.greenfox.helpmanager.login.exception.InvalidPasswordException;
import com.greenfox.helpmanager.login.exception.NoSuchAccountException;
import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  AccountRepository accountRepository;

  public void authenticate(String username, String password) throws Exception {
    Account account;
    if (!checkAccount(username)) {
      throw new NoSuchAccountException("Invalid username");
    } else {
      account = accountRepository.findAccountByUsername(username);
    }
    if (!checkPassword(password, account.getHash())) {
      throw new InvalidPasswordException("Invalid password");
    };
  }

  public boolean checkAccount(String username) {
    return (accountRepository.findAccountByUsername(username) != null);
  }

  public boolean checkPassword(String password, String pw_hashed) {
    return BCrypt.checkpw(password, pw_hashed);
  }
}