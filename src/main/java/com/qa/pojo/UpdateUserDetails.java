package com.qa.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDetails {
  String name;
  String job;
  String updatedAt;

  public UpdateUserDetails(String name, String job) {
    this.name = name;
    this.job = job;
  }
}
