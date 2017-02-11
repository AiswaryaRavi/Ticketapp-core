package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.model.User;
import com.aiswarya.util.ConnectionUtil;

public class TicketTransactionDao implements Dao<TicketTransaction> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(TicketTransaction t) {

		String sql = "insert into TICKET_TRANSACTIONS(USER_ID,SUBJECT,DESCRIPTION,PRIORITY_ID,DEPARTMENT_ID) values(?,?,?,?,?)";
		Object[] params = { t.getUserId().getId(), t.getSubject(), t.getDescription(), t.getPriorityId().getId(),
				t.getDepartmentId().getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void update(TicketTransaction t) throws PersistanceException {
		try {
			String sql = "update TICKET_TRANSACTIONS set DESCRIPTION=?,STATUS=? WHERE ID=?";
			Object[] params = { t.getDescription(), t.getStatus(), t.getId() };
			jdbcTemplate.update(sql, params);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("TicketId not exists", e);
		}

	}

	public void updateStatus(TicketTransaction t) {
		String sql = "update TICKET_TRANSACTIONS set STATUS=? WHERE ID=?";
		Object[] params = { t.getStatus(), t.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(TicketTransaction t) {

	}

	@Override
	public List<TicketTransaction> listAll() {
		String sql = "SELECT ID,USER_ID,SUBJECT,DESCRIPTION,CREATED_DATE,RESOLVED_DATE,DEPARTMENT_ID,ASSIGNED_EMPLOYEE_ID,STATUS FROM TICKET_TRANSACTIONS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));
	}

	private TicketTransaction convert(ResultSet rs) throws SQLException {
		return convert1(rs);
	}

	public TicketTransaction getStatus(int id) {
		String sql = "SELECT STATUS FROM TICKET_TRANSACTIONS WHERE ID=?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			TicketTransaction t = new TicketTransaction();
			t.setStatus(rs.getString("STATUS"));
			return t;
		});
	}

	public TicketTransaction getId(String emailid) {
		String sql = "SELECT ID FROM USERS WHERE EMAIL_ID=?";
		Object[] params = { emailid };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			TicketTransaction t = new TicketTransaction();
			t.setId(rs.getInt("ID"));
			return t;
		});
	}

	public List<TicketTransaction> listById(int userid) {
		String sql = "select ID,USER_ID,SUBJECT,DESCRIPTION,STATUS from TICKET_TRANSACTIONS WHERE USER_ID=?";
		Object[] params = { userid };

		return jdbcTemplate.query(sql, params, (rs, rowNum) -> convert1(rs));

	}

	public List<TicketTransaction> listByEmpId(int empid) {
		String sql = "SELECT ID,USER_ID,SUBJECT,DESCRIPTION,STATUS FROM TICKET_TRANSACTIONS WHERE ASSIGNED_EMPLOYEE_ID=?";
		Object[] params = { empid };

		return jdbcTemplate.query(sql, params, (rs, rowNum) -> convert3(rs));

	}

	private TicketTransaction convert3(ResultSet rs) throws SQLException {
		TicketTransaction tt = new TicketTransaction();
		User u = new User();
		tt.setId(rs.getInt("ID"));
		u.setId(rs.getInt("USER_ID"));
		tt.setUserId(u);
		tt.setSubject(rs.getString("SUBJECT"));
		tt.setDescription(rs.getString("DESCRIPTION"));

		tt.setStatus(rs.getString("STATUS"));
		return tt;
	}

	private TicketTransaction convert1(ResultSet rs) throws SQLException {
		return convert3(rs);
	}

	public TicketTransaction getTicketId(int id) {
		String sql = "SELECT ID FROM TICKET_TRANSACTIONS WHERE ID=?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			TicketTransaction t = new TicketTransaction();
			t.setId(rs.getInt("ID"));
			return t;
		});
	}

	public TicketTransaction getTId(int tid, int empid) throws PersistanceException {
		try {
			String sql = "SELECT ID FROM TICKET_TRANSACTIONS WHERE ID=? and ASSIGNED_EMPLOYEE_ID=?";
			Object[] params = { tid, empid };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				TicketTransaction t = new TicketTransaction();
				t.setId(rs.getInt("ID"));
				return t;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("TicketId not exists", e);
		}
	}

	public int getTickId(int userid, String subject, String description, int deptid, int priority)
			throws PersistanceException {
		try {
			String sql = "SELECT ID FROM TICKET_TRANSACTIONS WHERE USER_ID=? AND SUBJECT=? AND DESCRIPTION=? AND DEPARTMENT_ID=? AND PRIORITY_ID=?";
			Object[] params = { userid, subject, description, deptid, priority };
			return jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Invalid credentials", e);
		}
	}

	public String getTicketStatus(int ticketId) throws PersistanceException {
		try {
			String sql = "SELECT STATUS FROM TICKET_TRANSACTIONS WHERE ID=?";
			Object[] params = {ticketId};
			return jdbcTemplate.queryForObject(sql, params, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Ticket id does not exists", e);
		}
	}

	public int getUId(int ticketId) throws PersistanceException {
		try {
			String sql = "SELECT USER_ID FROM TICKET_TRANSACTIONS WHERE ID=?";
			Object[] params = { ticketId };
			return jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Invalid Ticket id", e);
		}
	}

	public String getemail(int uid) throws PersistanceException {
		try {
			String sql = "SELECT EMAIL_ID FROM USERS WHERE ID=?";
			Object[] params = { uid };
			return jdbcTemplate.queryForObject(sql, params, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Invalid user id", e);
		}
	}

}
