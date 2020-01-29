package com.stackroute.domain;

import lombok.Data;

@Data
public class UserDTO {
	private String emailId;
	private String password;
	private String role;
	private String loginType;
}