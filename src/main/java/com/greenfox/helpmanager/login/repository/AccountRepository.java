package com.greenfox.helpmanager.login.repository;

import com.greenfox.helpmanager.login.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
  Account findAccountByUsername(String username);
}
