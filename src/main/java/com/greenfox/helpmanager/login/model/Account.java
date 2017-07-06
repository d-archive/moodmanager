package com.greenfox.helpmanager.login.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

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
    this.status = "none";
  }

  public Account(String username, String hash, String token) {
    this.username = username;
    this.hash = hash;
    this.token = token;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMood() {
    return mood;
  }

  public void setMood(String mood) {
    this.mood = mood;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getStop() {
    return stop;
  }

  public void setStop(Date stop) {
    this.stop = stop;
  }

  public Date getRequest() {
    return request;
  }

  public void setRequest(Date request) {
    this.request = request;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
