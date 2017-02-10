package com.aiswarya.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.IssuesSolutions;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.util.ConnectionUtil;

public class UpdateSolutionDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	TicketTransaction tt = new TicketTransaction();
	TicketTransactionDao ttdao = new TicketTransactionDao();
	IssueSolutionDao i = new IssueSolutionDao();
	IssuesSolutions issue = new IssuesSolutions();
	EmployeesDao employeesdao = new EmployeesDao();

	public void updateSolution(String emailid,int ticketId, String solution)
			throws PersistanceException {

			ttdao.getTicketId(ticketId);
			int id = employeesdao.geteid(emailid);
			Integer tid = ttdao.getTId(ticketId, id).getId();
			if (tid != null) {
				String sql = "update TICKET_TRANSACTIONS set RESOLVED_DATE=NOW(),STATUS='RESOLVED' WHERE ID=?";
				Object[] params = { ticketId };
				jdbcTemplate.update(sql, params);
				String sql1 = "update ISSUE_SOLUTIONS SET SOLUTION=? WHERE TICKET_ID=?";
				Object[] params1 = { solution,ticketId };
				jdbcTemplate.update(sql1, params1);

			}

		}

	

}
