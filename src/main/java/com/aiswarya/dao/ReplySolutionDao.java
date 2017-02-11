package com.aiswarya.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.IssuesSolutions;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.util.ConnectionUtil;
import com.aiswarya.util.MailUtil;

public class ReplySolutionDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	TicketTransaction tt = new TicketTransaction();
	TicketTransactionDao ttdao = new TicketTransactionDao();
	IssueSolutionDao i = new IssueSolutionDao();
	IssuesSolutions issue = new IssuesSolutions();
	EmployeesDao employeesdao = new EmployeesDao();

	public void replySolution(String emailid, int ticketId, String solution) throws PersistanceException {
		try {
			ttdao.getTicketId(ticketId);
			int id = employeesdao.geteid(emailid);
			Integer tid = ttdao.getTId(ticketId, id).getId();
			String status = ttdao.getTicketStatus(ticketId);

			if (tid != null) {
				if (status != "closed") {

					String sql = "update TICKET_TRANSACTIONS set RESOLVED_DATE=NOW(),STATUS='RESOLVED' WHERE ID=?";
					Object[] params = { ticketId };
					jdbcTemplate.update(sql, params);
					tt.setId(tid);
					issue.setTicketId(tt);
					issue.setSolution(solution);
					i.save(issue);
					int uid = ttdao.getUId(ticketId);
					String email = ttdao.getemail(uid);
					try {
						MailUtil.sendSimpleMail1(email, "Ticket is resolved: for", ticketId,"solution is:"+solution);
					} catch (Exception e) {

					}
				}
			}

		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("TicketId not exists", e);

		}
	}
}
