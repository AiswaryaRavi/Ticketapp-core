package com.aiswarya.model;

import lombok.Data;

@Data

public class User {
	private Integer id;
	private String name;
	private String emailId;
	private String password;
	private Boolean active;

}
