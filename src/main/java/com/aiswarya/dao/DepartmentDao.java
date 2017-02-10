package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Departments;
import com.aiswarya.util.ConnectionUtil;

public class DepartmentDao implements Dao<Departments> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Departments dep) throws PersistanceException {
		try {
			String sql = "insert into DEPARTMENTS(NAME)values(?)";
			Object[] params = { dep.getDepartment() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistanceException("Given department already exists", e);
		}

	}

	@Override
	public void update(Departments dept) {
		String sql = "update DEPARTMENTS SET NAME=? WHERE ID=?";
		Object[] params = { dept.getDepartment(), dept.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(Departments dept) {
		String sql = "update DEPARTMENTS SET ACTIVE=? WHERE ID=?";
		Object[] params = { dept.getActive(), dept.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Departments> listAll() {
		String sql = "select ID,NAME,ACTIVE from DEPARTMENTS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));
	}

	private Departments convert(ResultSet rs) throws SQLException {
		Departments dept = new Departments();
		dept.setId(rs.getInt("ID"));
		dept.setDepartment(rs.getString("NAME"));
		dept.setActive(rs.getBoolean("ACTIVE"));
		return dept;
	}

	public Departments getDepartmentId(String department) throws PersistanceException {
		try {

			String sql = "SELECT ID FROM DEPARTMENTS WHERE NAME=?";
			Object[] params = { department };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				Departments dept = new Departments();
				dept.setId(rs.getInt("ID"));
				return dept;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("emailid does not exixts", e);
		}

	}
}
