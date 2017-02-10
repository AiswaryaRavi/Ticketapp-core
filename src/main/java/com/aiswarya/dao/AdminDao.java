package com.aiswarya.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.util.ConnectionUtil;

public class AdminDao {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	EmployeesDao employeesdao = new EmployeesDao();

	public void AdminDelete(String emailid, String password, int ticketId) throws PersistanceException {
		try {
			EmployeeLoginDao login = new EmployeeLoginDao();
			if (login.login(emailid, password)) {
				int id = employeesdao.getId(emailid, password);
				if ((employeesdao.getEmpId(id)) != null) {
					String sql = "DELETE FROM ISSUE_SOLUTIONS WHERE TICKET_ID=?";
					Object[] params = { ticketId };
					jdbcTemplate.update(sql, params);
					String sql1 = "DELETE FROM TICKET_TRANSACTIONS WHERE ID=?";
					Object[] params1 = { ticketId };
					jdbcTemplate.update(sql1, params1);
				}
			}

		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("TicketId not exists", e);
		}
	}
}
