package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.IssuesSolutions;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.util.ConnectionUtil;

public class IssueSolutionDao implements Dao<IssuesSolutions> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(IssuesSolutions t) throws PersistanceException {
		String sql1="INSERT INTO ISSUE_SOLUTIONS(TICKET_ID,SOLUTION)VALUES(?,?)";
				Object[] params1={t.getTicketId().getId(),t.getSolution()};
		jdbcTemplate.update(sql1, params1);
	}

	@Override
	public void update(IssuesSolutions t) {

	}

	@Override
	public void updateAsInactive(IssuesSolutions t) {

	}

	@Override
	public List<IssuesSolutions> listAll() {
		String sql = "SELECT ID,TICKET_ID,SOLUTION FROM ISSUE_SOLUTIONS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));
	}

	private IssuesSolutions convert(ResultSet rs) throws SQLException {
		IssuesSolutions ss = new IssuesSolutions();
		TicketTransaction tt = new TicketTransaction();
		ss.setId(rs.getInt("ID"));
		tt.setId(rs.getInt("TICKET_ID"));
		ss.setTicketId(tt);
		ss.setSolution(rs.getString("SOLUTION"));
		return ss;
	}

	
}
