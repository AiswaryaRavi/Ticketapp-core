package com.aiswarya.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class TicketTransaction {
	private Integer id;
	private User userId;
	private String subject;
	private String description;
	private Priority priorityId;
	private LocalDateTime createdDate;
	private LocalDateTime resolvedDate;
	private Departments departmentId;
	private Employee employeeId;
	private String status;

}
