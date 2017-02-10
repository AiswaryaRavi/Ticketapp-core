package com.aiswarya.model;

import lombok.Data;

@Data

public class IssuesSolutions {
	private Integer id;
	private TicketTransaction ticketId;
	private String solution;

}
