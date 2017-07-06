package com.greenfox.helpmanager.login.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  String username;
  String hash;
  String token;

  public Account() {
  }

  public Account(String username, String hash) {
    this.username = username;
    this.hash = hash;
  }

  public Account(String username, String hash, String token) {
    this.username = username;
    this.hash = hash;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getHash() {
    return hash;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}
