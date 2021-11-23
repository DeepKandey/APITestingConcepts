package com.qa.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDetails {
  String name;
  String job;
  String id;
  String createdAt;

  public CreateUserDetails(String name, String job) {
    this.name = name;
    this.job = job;
  }
}
