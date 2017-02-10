package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Priority;
import com.aiswarya.util.ConnectionUtil;

public class PriorityDao implements Dao<Priority> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Priority p) throws PersistanceException {
		try {
			String sql = "insert into PRIORITY(NAME)values(?)";
			Object[] params = { p.getName() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistanceException("Given priority name already exists", e);
		}

	}

	@Override
	public void update(Priority p) {
		String sql = "update PRIORITY SET NAME=? WHERE ID=?";
		Object[] params = { p.getName(), p.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(Priority p) {
		String sql = "update PRIORITY SET ACTIVE=? WHERE ID=?";
		Object[] params = { p.getActive(), p.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Priority> listAll() {
		String sql = "select ID,NAME,ACTIVE from ROLES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));

	}

	private Priority convert(ResultSet rs) throws SQLException {
		Priority priority = new Priority();
		priority.setId(rs.getInt("ID"));
		priority.setName(rs.getString("NAME"));
		priority.setActive(rs.getBoolean("ACTIVE"));
		return priority;
	}

	public Priority getPriorityId(String priority) throws PersistanceException {
		try{
		String sql = "SELECT ID FROM PRIORITY WHERE NAME=?";
		Object[] params = { priority };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			Priority p = new Priority();
			p.setId(rs.getInt("ID"));
			return p;
		});
		}
		catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("emailid does not exixts" , e);
		}
	}

}
