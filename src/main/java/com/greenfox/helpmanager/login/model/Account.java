package com.greenfox.helpmanager.login.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class Account {

  @Id
  String username;
  String hash;
  String token;
  String photo;
  String name;
  String mood;
  String status;
  Date start;
  Date stop;
  Date request;
  int count;

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
}
