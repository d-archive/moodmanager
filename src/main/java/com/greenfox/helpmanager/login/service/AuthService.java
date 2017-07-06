package com.greenfox.helpmanager.login.service;

import com.greenfox.helpmanager.login.exception.InvalidPasswordException;
import com.greenfox.helpmanager.login.exception.NoSuchAccountException;
import com.greenfox.helpmanager.login.model.Account;
import com.greenfox.helpmanager.login.repository.AccountRepository;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  AccountRepository accountRepository;

  public void giveToken(String username, String token) {
    Account account = accountRepository.findAccountByUsername(username);
    account.setToken(token);
    accountRepository.save(account);
  }

  public void authenticate(String username, String password) throws Exception {
    Account account;
    if (!checkAccount(username)) {
      throw new NoSuchAccountException("Invalid username");
    } else {
      account = accountRepository.findAccountByUsername(username);
    }
    if (!checkPassword(password, account.getHash())) {
      throw new InvalidPasswordException("Invalid password");
    }
    ;
  }

  public boolean checkAccount(String username) {
    return (accountRepository.findAccountByUsername(username) != null);
  }

  public boolean checkPassword(String password, String pw_hashed) {
    return BCrypt.checkpw(password, pw_hashed);
  }

  public boolean isRegisteredUser(String username) {
    List<Account> accountList = accountRepository.findAll();
    return accountList.contains(accountRepository.findAccountByUsername(username));
  }

  public String hashPassword(String password) {
    return BCrypt
        .hashpw(password, BCrypt.gensalt());
  }
}
