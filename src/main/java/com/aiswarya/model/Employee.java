package com.aiswarya.model;

import lombok.Data;

@Data

public class Employee {
	private Integer id;
	private Departments departmentId;
	private Role role;
	private String name;
	private String emailId;
	private String password;
	private Boolean active;
	private String status;

}
