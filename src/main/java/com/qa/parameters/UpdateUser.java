package com.qa.parameters;

public class UpdateUser {
	String name;
	String job;
	String UpdatedAt;

	public UpdateUser() {
	}

	public UpdateUser(String name, String job) {
		this.name = name;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(String UpdatedAt) {
		this.UpdatedAt = UpdatedAt;
	}
}
